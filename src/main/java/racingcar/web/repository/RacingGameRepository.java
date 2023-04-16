package racingcar.web.repository;

import org.springframework.stereotype.Repository;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.web.dao.CarDao;
import racingcar.web.dao.GameResultDao;
import racingcar.web.entity.CarEntity;
import racingcar.web.entity.GameResultEntity;

@Repository
public class RacingGameRepository {

    private final GameResultDao gameResultDao;
    private final CarDao carDao;

    public RacingGameRepository(GameResultDao gameResultDao, CarDao carDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
    }

    public void saveCars(Long gameResultId, Cars finalResult, Cars winnersResult) {

        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getName().getName(), car.getPosition().getPosition(), checkWinner(car, winnersResult), gameResultId))
                .forEach(carEntity -> carDao.insert(carEntity));
    }

    public Long saveGameResult(TryCount tryCount) {
        GameResultEntity gameResultEntity = new GameResultEntity(tryCount.getCount());
        return gameResultDao.insert(gameResultEntity);
    }

    private boolean checkWinner(Car currentCar, Cars winnersResult) {
        return winnersResult.getCars().contains(currentCar);
    }
}
