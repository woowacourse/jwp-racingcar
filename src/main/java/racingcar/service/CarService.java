package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.car.CarDao;
import racingcar.dao.car.dto.CarRegisterRequest;
import racingcar.domain.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarDao carDao;

    public CarService(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void registerCars(final RacingCars racingCars, final int savedId) {
        final List<CarRegisterRequest> requests =
                racingCars.getCars()
                          .stream()
                          .map(it -> new CarRegisterRequest(it.getName(),
                                                            it.getPosition(),
                                                            savedId))
                          .collect(Collectors.toList());

        carDao.save(requests);
    }
}
