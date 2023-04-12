package racingcar.dao;

import racingcar.model.Car;

import java.util.List;

public interface CarDao {
    void saveAll(int gameId, List<Car> cars);
}
