package racingcar.domain;

import static java.util.stream.Collectors.toList;
import static racingcar.exception.ExceptionMessage.DUPLICATE_CAR_NAME;
import static racingcar.exception.ExceptionMessage.EMPTY_CARS;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<String> cars) {
        validateDuplicateCarNames(cars);
        this.cars = cars.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicateCarNames(final List<String> cars) {
        List<String> noDuplicateCars = cars.stream().distinct().collect(Collectors.toList());
        if (noDuplicateCars.size() != cars.size()) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME.getMessage());
        }
    }

    public void moveCars(MoveStrategy moveStrategy) {
        for (Car car : cars) {
            moveCar(moveStrategy, car);
        }
    }

    private void moveCar(MoveStrategy moveStrategy, Car car) {
        if (moveStrategy.isMovable()) {
            car.move();
        }
    }

    public List<Car> getWinners() {
        int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.isSamePosition(maxPosition))
                .collect(toList());
    }

    private int getMaxPosition() {
        return cars.stream()
                .map(Car::getMovedLength)
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalStateException(EMPTY_CARS.getMessage()));
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
