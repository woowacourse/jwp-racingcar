package racingcar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;

public class FakeCarsRepository implements CarsRepository {

    private final Map<Integer, Cars> gameIdToCars = new HashMap<>();

    @Override
    public List<Car> findCars(final int gameId) {
        return gameIdToCars.get(gameId).getCars();
    }

    @Override
    public void save(final Cars cars, final int gameId) {
        gameIdToCars.put(gameId, cars);
    }

    @Override
    public List<Car> findWinner(final int gameId) {
        final Cars cars = gameIdToCars.get(gameId);
        final int max = findMaxPosition(cars);

        return cars.getCars()
                .stream()
                .filter(car -> car.matchPosition(max))
                .collect(Collectors.toList());
    }

    private int findMaxPosition(final Cars cars) {
        return cars.getCars()
                .stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElseThrow(IllegalArgumentException::new);
    }
}
