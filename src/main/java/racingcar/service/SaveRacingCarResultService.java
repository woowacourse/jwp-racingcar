package racingcar.service;

import static java.util.stream.Collectors.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Car;
import racingcar.domain.dto.RacingCarResult;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.CarDao;
import racingcar.repository.dao.PlayerDao;
import racingcar.repository.dao.WinnerDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.CarEntity;
import racingcar.repository.entity.PlayerEntity;
import racingcar.repository.entity.WinnerEntity;

@Service
public class SaveRacingCarResultService {

    private final PlayerDao playerDao;
    private final GameDao gameDao;
    private final CarDao carDao;
    private final WinnerDao winnerDao;

    public SaveRacingCarResultService(
        final PlayerDao playerDao,
        final GameDao gameDao,
        final CarDao carDao,
        final WinnerDao winnerDao
    ) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.carDao = carDao;
        this.winnerDao = winnerDao;
    }

    @Transactional
    public void save(final RacingCarResult racingCarResult) {
        final Set<String> winners = new HashSet<>(racingCarResult.getWinners());
        final List<Car> cars = racingCarResult.getCars();
        final int attempt = racingCarResult.getAttempt();

        final List<PlayerEntity> playerEntities = savePlayer(cars);
        final GameEntity gameEntity = saveGame(attempt);
        saveCar(gameEntity, toPositionByPlayerEntity(cars, playerEntities));
        saveWinners(winners, playerEntities, gameEntity);
    }

    private List<PlayerEntity> savePlayer(final List<Car> cars) {
        List<PlayerEntity> playerEntities = cars.stream()
            .map(car -> new PlayerEntity(car.getName()))
            .collect(toList());

        return playerEntities.stream()
            .map(this::getSavedPlayerEntity)
            .collect(toList());
    }

    private UserEntity getSavedUsersEntity(final UserEntity userEntity) {
        try {
            return playerDao.findByName(playerEntity.getName());
        } catch (EmptyResultDataAccessException e) {
            final long playerId = playerDao.save(playerEntity);
            return new PlayerEntity(playerId, playerEntity.getName());
        }
    }

    private GameEntity saveGame(final int attempt) {
        GameEntity gameEntity = new GameEntity(attempt);
        final long gameId = gameDao.save(gameEntity);
        return new GameEntity(gameId, gameEntity.getTrialCount(), gameEntity.getLastModifiedTime());
    }

    private Map<PlayerEntity, Integer> toPositionByPlayerEntity(
        final List<Car> cars,
        final List<PlayerEntity> playerEntities
    ) {
        final Map<String, Integer> positionByName = cars.stream()
            .collect(toMap(Car::getName, Car::getPosition));

        return playerEntities.stream()
            .collect(toMap(playerEntity -> playerEntity, playerEntity -> positionByName.get(playerEntity.getName())));
    }

    private void saveCar(final GameEntity gameEntity, final Map<PlayerEntity, Integer> positionByPlayerEntity) {
        positionByPlayerEntity.entrySet().stream()
            .map(entry -> getPositionEntity(gameEntity, entry))
            .forEach(carDao::save);
    }

    private CarEntity getPositionEntity(final GameEntity gameEntity, final Map.Entry<PlayerEntity, Integer> entry) {
        final PlayerEntity playerEntity = entry.getKey();
        final int position = entry.getValue();
        return new CarEntity(gameEntity.getId(), playerEntity.getId(), position);
    }

    private void saveWinners(final Set<String> winners,
        final List<PlayerEntity> playerEntities,
        final GameEntity gameEntity) {
        playerEntities.stream()
            .filter(playerEntity -> winners.contains(playerEntity.getName()))
            .map(playerEntity -> new WinnerEntity(gameEntity.getId(), playerEntity.getId()))
            .forEach(winnerDao::save);
    }
}
