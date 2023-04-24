package racingcar.dao;

import java.util.List;
import racingcar.domain.Car;

public interface CarDao {

    void insertCars(final List<Car> cars, final int gameId);

    void updateWinners(final List<Car> cars, final int gameId);

    void updatePositions(final List<Car> cars, final int gameId);

    List<Car> findWinners(int gameId);

    List<Car> findCars(int gameId);
}
