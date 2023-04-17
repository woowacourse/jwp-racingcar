package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.domain.Car;
import racingcar.utils.DtoMapper;

import java.util.List;

@Repository
public class CarRepository {

    private final CarDao carDao;

    public CarRepository(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void save(final Car car) {
        carDao.save(DtoMapper.toCarDto(car));
    }

    public void saveAll(final List<Car> carEntities) {
        for (Car car : carEntities) {
            save(car);
        }
    }
}
