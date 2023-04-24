package racingcar.repository.dao;

import racingcar.repository.entity.CarEntity;

public interface CarDao {

    long save(CarEntity carEntity);
}
