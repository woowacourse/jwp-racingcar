package racingcar.dao.car;

import java.util.List;

import racingcar.dao.entity.Car;
import racingcar.dto.CarDto;

public interface CarDao {
    void insertCar(List<CarDto> carDtos, long gameId);

    List<Car> findAllCars();
}
