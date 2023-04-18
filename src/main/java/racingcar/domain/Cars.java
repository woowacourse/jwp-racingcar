package racingcar.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.strategy.MovingStrategy;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void moveCars(final MovingStrategy movingStrategy) {
        for (Car car : cars) {
            car.move(movingStrategy.getRandomNumber());
        }
    }

    public Map<String, Integer> getTotalStatus() {
        Map<String, Integer> totalStatus = new HashMap<>();
        for (Car car : cars) {
            totalStatus.put(car.getName(), car.getPosition());
        }
        return totalStatus;
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();
        return cars.stream()
                .filter(x -> x.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private int findMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
