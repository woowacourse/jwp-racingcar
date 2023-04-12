package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.car.CarDao;
import racingcar.dao.car.dto.CarRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;

@Service
public class CarService {

    private final CarDao carDao;

    public CarService(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void registerCars(final RacingCars racingCars, final int savedId) {
        for (final Car car : racingCars.getCars()) {
            carDao.save(new CarRegisterRequest(car.getName(), car.getPosition(), savedId));
        }
    }
}
