package racingcar.model.car;

import org.springframework.util.StringUtils;
import racingcar.exception.CustomException;
import racingcar.exception.ExceptionStatus;
import racingcar.model.car.strategy.MovingStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final String SEPARATOR = ",";

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        validateDuplicateCarNames(cars);

        this.cars = cars;
    }

    public static Cars from(final List<Car> cars) {
        return new Cars(cars);
    }

    public static Cars of(final String carNames, final MovingStrategy movingStrategy) {
        validateEmptyInput(carNames);

        List<Car> cars = Arrays.stream(carNames.split(SEPARATOR, -1))
                .map(carName -> Car.of(carName, movingStrategy))
                .collect(Collectors.toList());

        return new Cars(cars);
    }

    private static void validateEmptyInput(final String carNames) {
        if (StringUtils.hasText(carNames)) {
            throw new CustomException(ExceptionStatus.EMPTY_INPUT_FORMAT);
        }
    }

    private void validateDuplicateCarNames(final List<Car> cars) {
        final List<String> carNames = cars.stream()
                .map(Car::getCarName)
                .collect(Collectors.toList());

        int carNamesCount = carNames.size();
        int distinctCarNamesCount = new HashSet<>(carNames).size();

        if (carNamesCount != distinctCarNamesCount) {
            throw new CustomException(ExceptionStatus.DUPLICATE_CAR_NAMES);
        }
    }

    public void moveCars() {
        cars.stream()
                .filter(Car::movable)
                .forEach(Car::moveForward);
    }

    public List<Car> getCarsCurrentInfo() {
        return cars.stream().collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getWinnerCars() {
        Integer maxPosition = Collections.max(cars.stream()
                .map(Car::getPosition)
                .collect(Collectors.toList()));

        return cars.stream()
                .filter(car -> car.isWinner(maxPosition))
                .collect(Collectors.toUnmodifiableList());
    }
}
