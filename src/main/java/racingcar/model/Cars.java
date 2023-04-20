package racingcar.model;

import racingcar.strategy.RacingNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void race(RacingNumberGenerator generator) {
        cars.forEach(car -> car.race(generator));
    }

    public Car getWinner() {
        return cars.stream()
                .max(Car::compareTo)
                .orElse(null);
    }

    public List<Car> findWinnerCars(final Car winner) {
        return cars.stream()
                .filter(car -> car.isSamePosition(winner))
                .map(car -> new Car(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<Car> findCars() {
        return cars;
    }
}
