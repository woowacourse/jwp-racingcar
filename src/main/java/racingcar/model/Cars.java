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
        Car winner = cars.stream()
                .max(Car::compareTo)
                .orElse(null);

        return winner;
    }

    public List<Car> findWinnerCars(Car winner) {
        return cars.stream()
                .filter(car -> car.isSamePosition(winner))
                .map(car -> new Car(car.getName()))
                .collect(Collectors.toList());
    }

    public List<Car> findCars() {
        return cars;
    }
}
