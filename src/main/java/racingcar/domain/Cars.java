package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.strategy.MovingStrategy;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void move(final MovingStrategy movingStrategy) {
        for (Car car : cars) {
            car.move(movingStrategy.getRandomNumber());
        }
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(x -> x.position() == maxPosition)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return cars.stream()
                .map(Car::position)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<Car> cars() {
        return Collections.unmodifiableList(cars);
    }
}
