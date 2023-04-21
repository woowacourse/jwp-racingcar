package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    public static final int MIN_PARTICIPANT_SIZE = 1;
    public static final int MAX_PARTICIPANT_SIZE = 5;
    public static final String PARTICIPANT_SIZE_ERROR_MESSAGE =
            "올바르지 않은 참여자수입니다.(" + MIN_PARTICIPANT_SIZE + "~" + MAX_PARTICIPANT_SIZE + ")";
    private static final String NAME_DUPLICATION_ERROR_MESSAGE = "자동차 이름은 중복될 수 없습니다.";

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
        if (cars.size() < MIN_PARTICIPANT_SIZE || MAX_PARTICIPANT_SIZE < cars.size()) {
            throw new IllegalArgumentException(PARTICIPANT_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplication(final List<Car> cars) {
        List<String> carNames = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        if (carNames.stream().distinct().count() != carNames.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void drive(NumberGenerator numberGenerator) {
        cars.forEach(car -> car.drive(numberGenerator.generate()));
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
