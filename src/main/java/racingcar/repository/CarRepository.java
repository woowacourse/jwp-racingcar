package racingcar.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import racingcar.dao.car.CarDao;
import racingcar.dao.car.dto.CarRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;

@Repository
public class CarRepository {

    private final CarDao carDao;

    public CarRepository(CarDao carDao) {
        this.carDao = carDao;
    }

    public void saveCars(final RacingCars racingCars, final int savedId) {
        for (final Car car : racingCars.getCars()) {
            carDao.save(new CarRegisterRequest(car.getName(), car.getPosition(), savedId));
        }
    }

    public List<Car> findAllByPlayResultId(final int playResultId) {
        return carDao.findAllByPlayResultId(playResultId);
    }
}
