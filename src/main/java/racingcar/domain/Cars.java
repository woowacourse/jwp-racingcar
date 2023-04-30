package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {

    private static final String SIZE_EXCEPTION = "[ERROR] 2개 이상의 자동차 이름을 입력해 주세요.";
    private static final String NAME_DUPLICATE_EXCEPTION = "[ERROR] 자동차 이름은 중복될 수 없습니다.";

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        validate(carNames);
        final List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    private static void validate(final List<String> carNames) {
        validateSizeOneOrZero(carNames);
        validateDuplicatedName(carNames);
    }


    private static void validateSizeOneOrZero(List<String> carNames) {
        int size = carNames.size();
        if (size == 0 || size == 1) {
            throw new IllegalArgumentException(SIZE_EXCEPTION);
        }
    }

    private static void validateDuplicatedName(List<String> carNames) {
        Set<String> duplicatedCheckListToSet = new HashSet<>(carNames);
        if (duplicatedCheckListToSet.size() != carNames.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATE_EXCEPTION);
        }
    }

    public List<String> findWinnerNames() {
        List<String> winnerNames = new ArrayList<>();
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }
        for (Car car : cars) {
            addWinnerName(winnerNames, maxPosition, car);
        }
        return winnerNames;
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    private void addWinnerName(List<String> winnerNames, int maxPosition, Car car) {
        if (car.getPosition() == maxPosition) {
            winnerNames.add(car.getName());
        }
    }
}
