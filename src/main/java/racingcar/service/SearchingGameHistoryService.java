package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.dto.GameResultResponse;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.GameWinnerDao;
import racingcar.repository.dao.PlayerDao;
import racingcar.repository.dao.PlayerPositionDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.GameWinnerEntity;
import racingcar.repository.entity.PlayerEntity;
import racingcar.repository.entity.PlayerPositionEntity;

@Service
public class SearchingGameHistoryService {

    private final PlayerDao playerDao;
    private final GameDao gameDao;
    private final PlayerPositionDao playerPositionDao;
    private final GameWinnerDao gameWinnerDao;

    public SearchingGameHistoryService(final PlayerDao playerDao,
                                       final GameDao gameDao,
                                       final PlayerPositionDao playerPositionDao,
                                       final GameWinnerDao gameWinnerDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.playerPositionDao = playerPositionDao;
        this.gameWinnerDao = gameWinnerDao;
    }

    public List<GameResultResponse> searchGameHistory() {
        return gameDao.findAll().stream()
                .map(GameEntity::getId)
                .map(gameId -> new GameResultResponse(getWinners(gameId), getCars(gameId)))
                .collect(Collectors.toList());
    }

    private List<Car> getCars(final long gameId) {
        final Map<String, Integer> positionByName = playerPositionDao.findByGameId(gameId).stream()
                .collect(Collectors.toMap(this::getPlayerName, PlayerPositionEntity::getPosition));

        final List<Car> cars = new ArrayList<>();
        positionByName.forEach((name, position) -> cars.add(Car.of(name, position)));

        return cars;
    }

    private String getPlayerName(final PlayerPositionEntity playerPositionEntity) {
        final long playerId = playerPositionEntity.getUserId();
        final PlayerEntity playerEntity = playerDao.findById(playerId);
        return playerEntity.getName();
    }

    private List<String> getWinners(final long gameId) {
        return gameWinnerDao.findByGameId(gameId).stream()
                .map(GameWinnerEntity::getUserId)
                .map(playerDao::findById)
                .map(PlayerEntity::getName)
                .collect(Collectors.toList());
    }
}
