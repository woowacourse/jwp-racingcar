package racingcar.model;

import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars = new ArrayList<>();

    public Cars(List<String> carsName) {
        for (String name : carsName) {
            cars.add(new Car(name));
        }
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }

    public void moveResult(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.moveByNumber(numberGenerator.generateNumber());
        }
    }

    public List<String> getWinners() {
        return cars.stream().filter(car -> car.checkPositionEqual(getMaxLocation()))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private Car getMaxLocation() {
        return cars.stream()
                .max(Car::compareTo)
                .get();
    }
}
