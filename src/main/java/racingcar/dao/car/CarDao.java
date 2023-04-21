package racingcar.dao.car;

import java.util.List;

import racingcar.dao.entity.Car;

public interface CarDao {
    void insertCar(List<Car> cars);

    List<Car> findAllCars();
}
