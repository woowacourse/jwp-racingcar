package racingcar.dao;

import racingcar.dao.entity.CarEntity;

import java.util.List;

public interface CarDao {

    void saveAll(List<CarEntity> carEntities);

    List<CarEntity> findEndedCars();
}
