package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 5;

    private final List<Car> cars;

    public Participants(List<Car> cars) {
        validate(cars);
        this.cars = new ArrayList<>(cars);
    }

    private void validate(final List<Car> cars) {
        validateSize(cars);
        validateDuplication(cars);
    }

    private void validateSize(final List<Car> cars) {
        if (cars.size() < MIN_COUNT || MAX_COUNT < cars.size()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 참여자수입니다.(" + MIN_COUNT + "~" + MAX_COUNT + ")");
        }
    }

    private void validateDuplication(final List<Car> cars) {
        List<String> carNames = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        if (carNames.stream().distinct().count() != carNames.size()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 중복될 수 없습니다.");
        }
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
}
