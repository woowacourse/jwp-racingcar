package racingcar.service;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.PlayerPositionDao;
import racingcar.repository.dao.PlayerDao;
import racingcar.repository.dao.GameWinnerDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.PlayerPositionEntity;
import racingcar.repository.entity.PlayerEntity;
import racingcar.repository.entity.GameWinnerEntity;

@Service
public class SaveRacingCarService {

    private final PlayerDao playerDao;
    private final GameDao gameDao;
    private final PlayerPositionDao playerPositionDao;
    private final GameWinnerDao gameWinnerDao;

    @Autowired
    public SaveRacingCarService(final PlayerDao playerDao,
                                final GameDao gameDao,
                                final PlayerPositionDao playerPositionDao,
                                final GameWinnerDao gameWinnerDao) {
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.playerPositionDao = playerPositionDao;
        this.gameWinnerDao = gameWinnerDao;
    }

    public void saveRacingCarResult(final RacingCarResult racingCarResult) {
        final Set<String> winners = new HashSet<>(racingCarResult.getWinners());
        final List<Car> cars = racingCarResult.getCars();
        final int attempt = racingCarResult.getAttempt();

        final List<PlayerEntity> userEntities = saveUsers(cars);
        final GameEntity gameEntity = saveGame(attempt);
        savePositions(gameEntity, mapPositionByUsersEntity(cars, userEntities));
        saveWinners(winners, userEntities, gameEntity);
    }

    private List<PlayerEntity> saveUsers(final List<Car> cars) {
        List<PlayerEntity> usersEntities = cars.stream()
                .map(car -> new PlayerEntity(car.getName()))
                .collect(toList());

        return usersEntities.stream()
                .map(this::getSavedUserEntity)
                .collect(toList());
    }

    private PlayerEntity getSavedUserEntity(final PlayerEntity playerEntity) {
        try {
            return playerDao.findByName(playerEntity.getName());
        } catch (EmptyResultDataAccessException e) {
            final long userId = playerDao.save(playerEntity);
            return new PlayerEntity(userId, playerEntity.getName());
        }
    }

    private GameEntity saveGame(final int attempt) {
        GameEntity gameEntity = new GameEntity(attempt);
        final long gameId = gameDao.save(gameEntity);
        return new GameEntity(gameId, gameEntity.getTrialCount(), gameEntity.getLastModifiedTime());
    }

    private Map<PlayerEntity, Integer> mapPositionByUsersEntity(final List<Car> cars,
                                                                final List<PlayerEntity> userEntities) {
        final Map<String, Integer> positionByName = cars.stream()
                .collect(toMap(Car::getName, Car::getPosition));

        return userEntities.stream()
                .collect(toMap(playerEntity -> playerEntity, playerEntity -> positionByName.get(playerEntity.getName())));
    }

    private void savePositions(final GameEntity gameEntity,
                               final Map<PlayerEntity, Integer> positionByUserEntity) {
        positionByUserEntity.entrySet().stream()
                .map(entry -> getPositionEntity(gameEntity, entry))
                .forEach(playerPositionDao::save);
    }

    private PlayerPositionEntity getPositionEntity(final GameEntity gameEntity,
                                                   final Entry<PlayerEntity, Integer> entry) {
        final PlayerEntity playerEntity = entry.getKey();
        final int position = entry.getValue();
        return new PlayerPositionEntity(gameEntity.getId(), playerEntity.getId(), position);
    }

    private void saveWinners(final Set<String> winners,
                             final List<PlayerEntity> userEntities,
                             final GameEntity gameEntity) {
        userEntities.stream()
                .filter(playerEntity -> winners.contains(playerEntity.getName()))
                .map(playerEntity -> new GameWinnerEntity(gameEntity.getId(), playerEntity.getId()))
                .forEach(gameWinnerDao::save);
    }
}
