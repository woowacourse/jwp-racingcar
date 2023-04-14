package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.entity.CarEntity;

@Repository
public class CarRepository {

    private final CarDao carDao;

    public CarRepository(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void saveCars(final Long gameId, final Cars finalResult, final Cars winnersResult) {
        finalResult.getCars()
                .stream()
                .map(car -> new CarEntity(car.getNameValue(), car.getPositionValue(), checkWinner(car, winnersResult), gameId))
                .forEach(carDao::save);
    }

    private boolean checkWinner(final Car currentCar, final Cars winnersResult) {
        return winnersResult.getCars().contains(currentCar);
    }
}
