package racingcar.repository;

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

    private final GameResultDao gameResultDao;
    private final CarDao carDao;

    public RacingGameRepository(final GameResultDao gameResultDao, final CarDao carDao) {
        this.gameResultDao = gameResultDao;
        this.carDao = carDao;
    }

    public void saveCars(final Long gameId, final Cars finalResult, final Cars winnersResult) {
        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getNameValue(), car.getPositionValue(), checkWinner(car, winnersResult), gameId))
                .forEach(carDao::save);
    }

    public Long saveGameResult(final TryCount tryCount) {
        GameResultEntity gameResultEntity = new GameResultEntity(tryCount.getCount());
        return gameResultDao.insert(gameResultEntity);
    }

    private boolean checkWinner(final Car currentCar, final Cars winnersResult) {
        return winnersResult.getCars().contains(currentCar);
    }
}
