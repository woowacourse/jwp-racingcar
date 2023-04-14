package racingcar.domain;

import static racingcar.exception.ExceptionMessage.DUPLICATE_CAR_NAME;

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
        List<String> noDuplicateCars = cars.stream()
                .distinct()
                .collect(Collectors.toList());
        if (noDuplicateCars.size() != cars.size()) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME.getMessage());
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
