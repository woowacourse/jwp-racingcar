package racingcar.domain;

import racingcar.domain.engine.Engine;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(List<Car> cars) {
        return new Cars(cars);
    }

    public static Cars createByNames(Names names) {
        List<Car> cars = names.getNames().stream()
                .map(Car::from)
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    public void moveCars(Engine engine) {
        for (Car car : cars) {
            car.tryMove(engine);
        }
    }

    public Cars getWinners() {
        int maxPosition = getMaxPosition();
        List<Car> result = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());

        return new Cars(result);
    }

    private int getMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .orElseThrow();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
