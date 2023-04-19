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

    public void moveResult(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.moveByNumber(numberGenerator.generateNumber());
        }
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }

    public List<Car> getWinners() {
        return cars.stream().filter(car -> car.checkPositionEqual(getMaxPosition()))
                .collect(Collectors.toList());
    }

    private Car getMaxPosition() {
        return cars.stream()
                .max(Car::compareTo)
                .orElseThrow();
    }
}
