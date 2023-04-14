package racingcar.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public void moveAll() {
        cars.stream()
                .filter(MovableStrategy::isMove)
                .forEach(Car::updateDistance);
    }

    public List<Car> getWinner() {
        int winnerDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .orElseThrow();
        return cars.stream()
                .filter(car2 -> car2.getDistance() == winnerDistance)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }
}
