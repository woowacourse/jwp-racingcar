package racingcar.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {

    private static final String EXISTS_DUPLICATE_CAR_NAME_ERROR = "자동차명은 중복되어선 안됩니다.";

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateDuplicateCar(cars);
        this.cars = cars;
    }

    private void validateDuplicateCar(List<Car> cars) {
        Set<Car> duplicateChecker = new HashSet<>(cars);

        if (duplicateChecker.size() != cars.size()) {
            throw new IllegalArgumentException(EXISTS_DUPLICATE_CAR_NAME_ERROR);
        }
    }

    public void moveAll() {
        cars.stream()
                .filter(Car::isMove)
                .forEach(Car::move);
    }

    public String getWinner() {
        int winnerDistance = cars.stream()
                .mapToInt(Car::getDistance)
                .max()
                .getAsInt();

        return cars.stream()
                .filter(car -> car.getDistance() == winnerDistance)
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public List<Car> getCars() {
        return cars;
    }
}
