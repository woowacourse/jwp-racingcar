package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.domain.Car;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.PositionDao;
import racingcar.repository.dao.UserDao;
import racingcar.repository.dao.WinnerDao;
import racingcar.repository.entity.GameEntity;
import racingcar.repository.entity.PositionEntity;

@Service
public class FindRecordService {

    private final UserDao userDao;
    private final GameDao gameDao;
    private final PositionDao positionDao;
    private final WinnerDao winnerDao;

    public FindRecordService(final UserDao userDao,
                             final GameDao gameDao,
                             final PositionDao positionDao,
                             final WinnerDao winnerDao) {
        this.userDao = userDao;
        this.gameDao = gameDao;
        this.positionDao = positionDao;
        this.winnerDao = winnerDao;
    }

    public List<RacingCarResult> findAllRecords() {
        final List<Long> gameIds = findAllGameIds();
        return gameIds.stream()
            .map(this::findSingleRecord)
            .collect(Collectors.toList());
    }

    private List<Long> findAllGameIds() {
        final List<GameEntity> games = gameDao.findAll();
        return games.stream()
            .map(GameEntity::getId)
            .collect(Collectors.toList());
    }

    private RacingCarResult findSingleRecord(final Long gameId) {
        List<PositionEntity> positionEntities = positionDao.findByGameId(gameId);
        List<Car> cars = positionEntities.stream()
            .map(positionEntity -> Car.of(findUserName(positionEntity.getUserId()), positionEntity.getPosition()))
            .collect(Collectors.toList());

        final int maxPosition = cars.stream()
            .mapToInt(Car::getPosition)
            .max()
            .orElseThrow(IllegalStateException::new);

        final List<String> winners = cars.stream()
            .filter(car -> car.getPosition() == maxPosition)
            .map(Car::getName)
            .collect(Collectors.toList());

        return new RacingCarResult(winners, cars);
    }

    private String findUserName(final Long userId) {
        return userDao.findById(userId).getName();
    }
}
