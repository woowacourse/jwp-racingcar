package racingcar.service;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;

public interface CarsRepository {

    List<Car> findCars(int gameId);

    void save(Cars cars, int gameId);

    List<Car> findWinner(int gameId);
}
