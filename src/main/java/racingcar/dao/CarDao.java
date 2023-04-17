package racingcar.dao;

import racingcar.dao.entity.CarEntity;
import racingcar.model.Car;

import java.util.List;

public interface CarDao {
    void saveAll(int gameId, List<Car> cars, List<Car> winners);

    List<CarEntity> findAllById(int gameId);
}
