package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.utils.NumberGenerator;

public class Participants {

    private final List<Car> cars;

    public Participants(List<Car> cars) {
        validateDuplication(cars);
        this.cars = new ArrayList<>(cars);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void drive(NumberGenerator numberGenerator) {
        cars.forEach(car -> car.drive(numberGenerator));
    }

    public List<Car> findWinners() {
        int maxDistance = cars.stream()
                .map(Car::getDrivenDistance)
                .max(Comparator.naturalOrder())
                .orElse(0);
        return cars.stream()
                .filter(car -> car.getDrivenDistance() == maxDistance)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplication(final List<Car> cars) {
        final String DUPLICATE_CAR_NAME = "[ERROR] 자동차 이름은 중복될 수 없습니다.";

        List<String> carNames = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        if (carNames.stream().distinct().count() != carNames.size()) {
            throw new IllegalArgumentException(DUPLICATE_CAR_NAME);
        }
    }
}
