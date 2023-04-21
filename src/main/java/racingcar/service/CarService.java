package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.entity.CarEntity;
import racingcar.domain.RacingCars;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarDao carDao;

    public CarService(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void registerCars(final RacingCars racingCars, final Integer savedId) {
        final List<CarEntity> requests =
                racingCars.getCars()
                          .stream()
                          .map(it -> new CarEntity(it.getName(),
                                                   it.getPosition(),
                                                   savedId,
                                                   LocalDateTime.now()))
                          .collect(Collectors.toList());

        carDao.save(requests);
    }
}
