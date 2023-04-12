package racingcar.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.dao.GameResultDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;

@Repository
public class RacingGameRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GameResultDao gameResultDao;

    @Autowired
    private CarDao carDao;

    public void saveCars(Long gameResultId, Cars finalResult, Cars winnersResult) {

        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getName().getName(), car.getPosition().getPosition(), checkWinner(car, winnersResult), gameResultId))
                .forEach(carEntity -> carDao.save(carEntity));
    }

    public Long saveGameResult(TryCount tryCount) {
        GameResultEntity gameResultEntity = new GameResultEntity(tryCount.getCount());
        return gameResultDao.insert(gameResultEntity);
    }

    private boolean checkWinner(Car currentCar, Cars winnersResult) {
        return winnersResult.getCars().contains(currentCar);
    }
}
