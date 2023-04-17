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
import racingcar.repository.dao.PositionDao;
import racingcar.repository.dao.UserDao;
import racingcar.repository.dao.WinnerDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.PositionEntity;
import racingcar.repository.entity.UserEntity;
import racingcar.repository.entity.WinnerEntity;

@Service
public class SaveRacingCarService {

    private final UserDao userDao;
    private final GameDao gameDao;
    private final PositionDao positionDao;
    private final WinnerDao winnerDao;

    @Autowired
    public SaveRacingCarService(final UserDao userDao,
                                final GameDao gameDao,
                                final PositionDao positionDao,
                                final WinnerDao winnerDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.positionDao = positionDao;
        this.winnerDao = winnerDao;
    }

    public void saveRacingCarResult(final RacingCarResult racingCarResult) {
        final Set<String> winners = new HashSet<>(racingCarResult.getWinners());
        final List<Car> cars = racingCarResult.getCars();
        final int attempt = racingCarResult.getAttempt();

        final List<UserEntity> userEntities = saveUsers(cars);
        final GameEntity gameEntity = saveGame(attempt);
        savePositions(gameEntity, mapPositionByUsersEntity(cars, userEntities));
        saveWinners(winners, userEntities, gameEntity);
    }

    private List<UserEntity> saveUsers(final List<Car> cars) {
        List<UserEntity> usersEntities = cars.stream()
                .map(car -> new UserEntity(car.getName()))
                .collect(toList());

        return usersEntities.stream()
                .map(this::getSavedUserEntity)
                .collect(toList());
    }

    private UserEntity getSavedUserEntity(final UserEntity userEntity) {
        try {
            return userDao.findByName(userEntity.getName());
        } catch (EmptyResultDataAccessException e) {
            final long userId = userDao.save(userEntity);
            return new UserEntity(userId, userEntity.getName());
        }
    }

    private GameEntity saveGame(final int attempt) {
        GameEntity gameEntity = new GameEntity(attempt);
        final long gameId = gameDao.save(gameEntity);
        return new GameEntity(gameId, gameEntity.getTrialCount(), gameEntity.getLastModifiedTime());
    }

    private Map<UserEntity, Integer> mapPositionByUsersEntity(final List<Car> cars,
                                                              final List<UserEntity> userEntities) {
        final Map<String, Integer> positionByName = cars.stream()
                .collect(toMap(Car::getName, Car::getPosition));

        return userEntities.stream()
                .collect(toMap(userEntity -> userEntity, userEntity -> positionByName.get(userEntity.getName())));
    }

    private void savePositions(final GameEntity gameEntity,
                               final Map<UserEntity, Integer> positionByUserEntity) {
        positionByUserEntity.entrySet().stream()
                .map(entry -> getPositionEntity(gameEntity, entry))
                .forEach(positionDao::save);
    }

    private PositionEntity getPositionEntity(final GameEntity gameEntity,
                                             final Entry<UserEntity, Integer> entry) {
        final UserEntity userEntity = entry.getKey();
        final int position = entry.getValue();
        return new PositionEntity(gameEntity.getId(), userEntity.getId(), position);
    }

    private void saveWinners(final Set<String> winners,
                             final List<UserEntity> userEntities,
                             final GameEntity gameEntity) {
        userEntities.stream()
                .filter(userEntity -> winners.contains(userEntity.getName()))
                .map(userEntity -> new WinnerEntity(gameEntity.getId(), userEntity.getId()))
                .forEach(winnerDao::save);
    }
}
