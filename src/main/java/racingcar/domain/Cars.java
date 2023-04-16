package racingcar.domain;

import racingcar.domain.engine.Engine;
import racingcar.exception.ErrorCode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
    }

    public Cars(List<Name> carNames, Engine engine) {
        cars = carNames.stream().map(name -> new Car(name, engine))
                .collect(Collectors.toList());
        validateNameDuplication(carNames);
    }

    public void moveCars() {
        for (Car car : cars) {
            car.tryMove();
        }
    }

    public Cars getWinners() {
        int maxPosition = getMaxPosition();
        List<Car> result = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());

        return new Cars(result);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    private int getMaxPosition() {
        return cars.stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .get();
    }

    private void validateNameDuplication(List<Name> carNames) {
        if (carNames.stream().distinct().count() != carNames.size()) {
            throw new IllegalArgumentException(ErrorCode.INVALID_NAME_DUPLICATE.getMessage());
        }
    }
}
