package racingcar.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.entity.GameResultEntity;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDao;
    private final CarDao carDao;

    public RacingGameRepository(final GameResultDao gameResultDao, final CarDao carDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
    }

    @Transactional
    public void save(final RacingGame racingGame) {
        final Long gameId = gameResultDao.save(new GameResultEntity(racingGame.getTryCountValue()));
        final List<Car> collect = racingGame.getCars()
                .stream()
                .map(car -> new Car(gameId, car.getNameValue(), car.getPositionValue(), car.isWinner()))
                .collect(Collectors.toList());
        carDao.saveAll(collect);
    }
}
