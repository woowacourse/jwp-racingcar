package racingcar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.dao.car.CarDao;
import racingcar.dao.car.dto.CarRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.util.NumberGenerator;

@Service
public class CarService {

    private final CarDao carDao;
    private final NumberGenerator numberGenerator;


    public CarService(final CarDao carDao, final NumberGenerator numberGenerator) {
        this.carDao = carDao;
        this.numberGenerator = numberGenerator;
    }

    public RacingCars race(final GameInfoRequest gameInfoRequest) {
        final String names = gameInfoRequest.getNames();
        final RacingCars racingCars = RacingCars.makeCars(names);

        final int tryCount = gameInfoRequest.getCount();

        racingCars.moveAllCars(tryCount, numberGenerator);

        return racingCars;
    }

    public void registerCars(final RacingCars racingCars, final int savedId) {
        for (final Car car : racingCars.getCars()) {
            carDao.save(new CarRegisterRequest(car.getName(), car.getPosition(), savedId));
        }
    }

    public List<Car> getAllCars(final int playResultId) {
        return carDao.findAllByPlayResultId(playResultId);
    }
}
