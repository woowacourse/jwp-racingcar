package racingcar.domain;

import racingcar.utils.MovingStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Cars implements Iterable<Car> {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public Cars(Cars cars) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            result.add(new Car(car));
        }
        this.cars = result;
    }

    public void moveCars(MovingStrategy strategy) {
        for (Car car : cars) {
            car.move(strategy.movable());
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> calculateWinners(List<Car> cars) {
        int maxPosition = getMaxPosition(cars);
        return cars.stream()
                .filter(car -> car.getPosition() == (maxPosition))
                .collect(Collectors.toList());
    }

    private int getMaxPosition(List<Car> cars) {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compare)
                .orElse(Integer.MIN_VALUE);
    }

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }
}
