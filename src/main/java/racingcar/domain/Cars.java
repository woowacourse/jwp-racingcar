package racingcar.domain;

import racingcar.domain.movingstrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Cars {

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public Cars move(final MovingStrategy strategy) {
        for (Car car : cars) {
            car.move(strategy.movable());
        }
        return updateWinner(getMaxPosition());
    }

    private Cars updateWinner(final Position maxPosition) {
        final List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition().equals(maxPosition)) {
                result.add(new Car(car, true));
                continue;
            }
            result.add(new Car(car, false));
        }
        return new Cars(result);
    }

    private Position getMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Position::compareTo)
                .orElseGet(Position::create);
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}
