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
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.PositionDao;
import racingcar.repository.dao.UserDao;
import racingcar.repository.dao.WinnerDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.PositionEntity;
import racingcar.repository.entity.UserEntity;
import racingcar.repository.entity.WinnerEntity;
import racingcar.service.dto.RacingCarResult;

@Service
public class SaveRacingCarResultService {

    private final UserDao userDao;
    private final GameDao gameDao;
    private final PositionDao positionDao;
    private final WinnerDao winnerDao;

    public SaveRacingCarResultService(
        final UserDao userDao,
        final GameDao gameDao,
        final PositionDao positionDao,
        final WinnerDao winnerDao
    ) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.positionDao = positionDao;
        this.winnerDao = winnerDao;
    }

    @Transactional
    public void saveRacingCarResult(final RacingCarResult racingCarResult) {
        final Set<String> winners = new HashSet<>(racingCarResult.getWinners());
        final List<Car> cars = racingCarResult.getCars();
        final int attempt = racingCarResult.getAttempt();

        final List<UserEntity> userEntities = saveUsers(cars);
        if (isUserSaved(userEntities)) {
            return;
        }
        final GameEntity gameEntity = saveGame(attempt);
        savePosition(gameEntity, mapToPositionByUserEntity(cars, userEntities));
        saveWinners(winners, userEntities, gameEntity);
    }

    private List<UserEntity> saveUsers(final List<Car> cars) {
        List<UserEntity> userEntities = cars.stream()
            .map(car -> new UserEntity(car.getName()))
            .collect(toList());

        return userEntities.stream()
            .map(this::getSavedUsersEntity)
            .collect(toList());
    }

    private UserEntity getSavedUsersEntity(final UserEntity userEntity) {
        try {
            return userDao.findByName(userEntity.getName());
        } catch (EmptyResultDataAccessException e) {
            final long userId = userDao.save(userEntity);
            return new UserEntity(userId, userEntity.getName());
        }
    }

    private boolean isUserSaved(final List<UserEntity> userEntities) {
        return userEntities.get(0).getId() == null;
    }

    private GameEntity saveGame(final int attempt) {
        GameEntity gameEntity = new GameEntity(attempt);
        final long gameId = gameDao.save(gameEntity);
        return new GameEntity(gameId, gameEntity.getTrialCount(), gameEntity.getLastModifiedTime());
    }

    private Map<UserEntity, Integer> mapToPositionByUserEntity(final List<Car> cars,
        final List<UserEntity> usersEntities) {
        final Map<String, Integer> positionByName = cars.stream()
            .collect(toMap(Car::getName, Car::getPosition));

        return usersEntities.stream()
            .collect(toMap(userEntity -> userEntity, userEntity -> positionByName.get(userEntity.getName())));
    }

    private void savePosition(final GameEntity gameEntity, final Map<UserEntity, Integer> positionByUsersEntity) {
        positionByUsersEntity.entrySet().stream()
            .map(entry -> getPositionEntity(gameEntity, entry))
            .forEach(positionDao::save);
    }

    private PositionEntity getPositionEntity(final GameEntity gameEntity, final Map.Entry<UserEntity, Integer> entry) {
        final UserEntity userEntity = entry.getKey();
        final int position = entry.getValue();
        return new PositionEntity(gameEntity.getId(), userEntity.getId(), position);
    }

    private void saveWinners(final Set<String> winners,
        final List<UserEntity> usersEntities,
        final GameEntity gameEntity) {
        usersEntities.stream()
            .filter(userEntity -> winners.contains(userEntity.getName()))
            .map(userEntity -> new WinnerEntity(gameEntity.getId(), userEntity.getId()))
            .forEach(winnerDao::save);
    }
}
