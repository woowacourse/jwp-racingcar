package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.domain.RacingCars;
import racingcar.entity.CarEntity;
import racingcar.service.mapper.CarMapper;

import java.util.List;

@Service
public class CarService {

    private final CarDao carDao;
    private final CarMapper carMapper;

    public CarService(final CarDao carDao, final CarMapper carMapper) {
        this.carDao = carDao;
        this.carMapper = carMapper;
    }

    public void registerCars(final RacingCars racingCars, final Integer savedId) {
        final List<CarEntity> requests = carMapper.mapToCarEntitiesFrom(racingCars, savedId);

        carDao.save(requests);
    }
}
