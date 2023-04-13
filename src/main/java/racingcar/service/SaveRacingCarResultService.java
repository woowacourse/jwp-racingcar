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
import racingcar.repository.dao.GameUsersPositionDao;
import racingcar.repository.dao.GameWinUsersDao;
import racingcar.repository.dao.UsersDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.GameUsersPositionEntity;
import racingcar.repository.entity.GameWinUsersEntity;
import racingcar.repository.entity.UsersEntity;

@Service
public class SaveRacingCarResultService {

    private final UsersDao usersDao;
    private final GameDao gameDao;
    private final GameUsersPositionDao gameUsersPositionDao;
    private final GameWinUsersDao gameWinUsersDao;

    @Autowired
    public SaveRacingCarResultService(final UsersDao usersDao,
                                      final GameDao gameDao,
                                      final GameUsersPositionDao gameUsersPositionDao,
                                      final GameWinUsersDao gameWinUsersDao) {
        this.usersDao = usersDao;
        this.gameDao = gameDao;
        this.gameUsersPositionDao = gameUsersPositionDao;
        this.gameWinUsersDao = gameWinUsersDao;
    }

    public void saveRacingCarResult(final RacingCarResult racingCarResult) {
        final Set<String> winners = new HashSet<>(racingCarResult.getWinners());
        final List<Car> cars = racingCarResult.getCars();
        final int attempt = racingCarResult.getAttempt();

        final List<UsersEntity> usersEntities = saveUsers(cars);
        final GameEntity gameEntity = saveGame(attempt);
        saveGameUsersPosition(gameEntity, mapPositionByUsersEntity(cars, usersEntities));
        saveGameWinUsers(winners, usersEntities, gameEntity);
    }

    private List<UsersEntity> saveUsers(final List<Car> cars) {
        List<UsersEntity> usersEntities = cars.stream()
                .map(car -> new UsersEntity(car.getName()))
                .collect(toList());

        return usersEntities.stream()
                .map(this::getSavedUsersEntity)
                .collect(toList());
    }

    private UsersEntity getSavedUsersEntity(final UsersEntity usersEntity) {
        try {
            return usersDao.findByName(usersEntity.getName());
        } catch (EmptyResultDataAccessException e) {
            final long userId = usersDao.save(usersEntity);
            return new UsersEntity(userId, usersEntity.getName());
        }
    }

    private GameEntity saveGame(final int attempt) {
        GameEntity gameEntity = new GameEntity(attempt);
        final long gameId = gameDao.save(gameEntity);
        return new GameEntity(gameId, gameEntity.getTrialCount(), gameEntity.getLastModifiedTime());
    }

    private Map<UsersEntity, Integer> mapPositionByUsersEntity(final List<Car> cars,
                                                               final List<UsersEntity> usersEntities) {
        final Map<String, Integer> positionByName = cars.stream()
                .collect(toMap(Car::getName, Car::getPosition));

        return usersEntities.stream()
                .collect(toMap(usersEntity -> usersEntity, usersEntity -> positionByName.get(usersEntity.getName())));
    }

    private void saveGameUsersPosition(final GameEntity gameEntity,
                                       final Map<UsersEntity, Integer> positionByUsersEntity) {
        positionByUsersEntity.entrySet().stream()
                .map(entry -> getGameUsersPositionEntity(gameEntity, entry))
                .forEach(gameUsersPositionDao::save);
    }

    private GameUsersPositionEntity getGameUsersPositionEntity(final GameEntity gameEntity,
                                                               final Entry<UsersEntity, Integer> entry) {
        final UsersEntity usersEntity = entry.getKey();
        final int position = entry.getValue();
        return new GameUsersPositionEntity(gameEntity.getId(), usersEntity.getId(), position);
    }

    private void saveGameWinUsers(final Set<String> winners,
                                  final List<UsersEntity> usersEntities,
                                  final GameEntity gameEntity) {
        usersEntities.stream()
                .filter(usersEntity -> winners.contains(usersEntity.getName()))
                .map(usersEntity -> new GameWinUsersEntity(gameEntity.getId(), usersEntity.getId()))
                .forEach(gameWinUsersDao::save);
    }
}
