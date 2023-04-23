package racingcar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import racingcar.domain.AttemptNumber;
import racingcar.domain.Car;
import racingcar.domain.RacingCarGame;
import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.GameWinnerDao;
import racingcar.repository.dao.PlayerDao;
import racingcar.repository.dao.PlayerPositionDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.GameWinnerEntity;
import racingcar.repository.entity.PlayerEntity;
import racingcar.repository.entity.PlayerPositionEntity;
import racingcar.utils.NumberGenerator;

@Service
public class RacingCarGameService {

    private final NumberGenerator numberGenerator;
    private final PlayerDao playerDao;
    private final GameDao gameDao;
    private final PlayerPositionDao playerPositionDao;
    private final GameWinnerDao gameWinnerDao;

    public RacingCarGameService(final NumberGenerator numberGenerator,
                                final PlayerDao playerDao,
                                final GameDao gameDao,
                                final PlayerPositionDao playerPositionDao,
                                final GameWinnerDao gameWinnerDao) {
        this.numberGenerator = numberGenerator;
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.playerPositionDao = playerPositionDao;
        this.gameWinnerDao = gameWinnerDao;
    }

    public GameResultResponse raceCar(final GameInitializationRequest gameInitializationRequest) {
        final RacingCarGame racingCarGame = getRacingCarGame(gameInitializationRequest);
        final AttemptNumber attemptNumber = racingCarGame.getAttemptNumber();
        final long gameId = gameDao.save(new GameEntity(attemptNumber.getAttemptNumber()));

        racingCarGame.play();
        final List<Car> cars = racingCarGame.getCars();
        final List<String> winners = racingCarGame.findWinners();

        final List<PlayerEntity> playerEntities = new ArrayList<>();
        final Map<Long, Integer> positionByPlayerId = new HashMap<>();

        for (final Car car : cars) {
            final PlayerEntity savedPlayerEntity = getSavedPlayerEntity(new PlayerEntity(car.getName()));

            playerEntities.add(savedPlayerEntity);
            positionByPlayerId.put(savedPlayerEntity.getId(), car.getPosition());
        }

        savePositions(positionByPlayerId, gameId);
        saveWinners(winners, playerEntities, gameId);

        return new GameResultResponse(winners, cars);
    }

    private RacingCarGame getRacingCarGame(final GameInitializationRequest gameInitializationRequest) {
        final List<String> names = List.of(gameInitializationRequest.getNames().split(","));
        final int count = gameInitializationRequest.getCount();

        return new RacingCarGame(names, count, numberGenerator);
    }

    private PlayerEntity getSavedPlayerEntity(final PlayerEntity playerEntity) {
        try {
            return playerDao.findByName(playerEntity.getName());
        } catch (EmptyResultDataAccessException e) {
            final long userId = playerDao.save(playerEntity);
            return new PlayerEntity(userId, playerEntity.getName());
        }
    }

    private void savePositions(final Map<Long, Integer> positionByPlayerId, final long gameId) {
        positionByPlayerId.keySet().stream()
                .map(playerId -> new PlayerPositionEntity(gameId, playerId, positionByPlayerId.get(playerId)))
                .forEach(playerPositionDao::save);
    }

    private void saveWinners(final List<String> winners, final List<PlayerEntity> playerEntities, final long gameId) {
        playerEntities.stream()
                .filter(playerEntity -> winners.contains(playerEntity.getName()))
                .map(playerEntity -> new GameWinnerEntity(gameId, playerEntity.getId()))
                .forEach(gameWinnerDao::save);
    }
}
