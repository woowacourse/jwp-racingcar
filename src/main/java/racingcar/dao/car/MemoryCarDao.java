package racingcar.dao.car;

import java.util.ArrayList;
import java.util.List;

import racingcar.dao.entity.Car;

public class MemoryCarDao implements CarDao {
    private final List<Car> carTable = new ArrayList<>();

    @Override
    public void insertCar(List<Car> cars) {
        carTable.addAll(cars);
    }

    @Override
    public List<Car> findAllCars() {
        return List.copyOf(carTable);
    }
}
