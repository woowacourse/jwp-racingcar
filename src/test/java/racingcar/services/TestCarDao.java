package racingcar.services;

import java.util.List;

import racingcar.dao.car.CarDao;
import racingcar.dao.entity.Car;

public class TestCarDao implements CarDao {
    @Override
    public void insertCar(List<Car> cars) {

    }

    @Override
    public List<Car> findAllCars() {
        return List.of(
                new Car(1L, "폴로", 4),
                new Car(1L, "이리내", 6)
        );
    }
}
