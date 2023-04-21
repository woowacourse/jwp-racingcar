package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.dao.CarDao;
import racingcar.domain.RacingGame;
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

    public void registerCars(final RacingGame racingGame, final Integer savedId) {
        final List<CarEntity> carEntities =
                carMapper.mapToCarEntitiesFrom(racingGame, savedId);

        carDao.save(carEntities);
    }
}
