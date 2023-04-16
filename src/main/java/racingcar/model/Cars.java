package racingcar.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.util.NumberGenerator;

public class Cars {
    private final List<Car> cars = new ArrayList<>();

    public Cars(Names carsName) {
        for (String name : carsName.getNames()) {
            cars.add(new Car(name));
        }
    }

    public void moveResult(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.moveByNumber(numberGenerator.generateNumber());
        }
    }

    public List<String> getWinners() {
        return cars.stream().filter(car -> car.checkPositionEqual(getMaxPosition()))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private Car getMaxPosition() {
        return cars.stream()
                .max(Car::compareTo)
                .get();
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
