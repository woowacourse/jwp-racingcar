package racingcar.repository;

import org.springframework.stereotype.Repository;
import racingcar.dao.CarDao;
import racingcar.entity.CarEntity;

import java.util.List;

@Repository
public class CarRepository {

    private final CarDao carDao;

    public CarRepository(final CarDao carDao) {
        this.carDao = carDao;
    }

    public void save(final CarEntity carEntity) {
        carDao.save(carEntity);
    }

    public void saveAll(final List<CarEntity> carEntities) {
        for (CarEntity carEntity : carEntities) {
            save(carEntity);
        }
    }
}
