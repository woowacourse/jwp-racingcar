package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Cars {
    private static final int MINIMUM_NUMBER_OF_CARS = 2;
    private static final int MAXIMUM_NUMBER_OF_CARS = 50;

    private final List<Car> racingCars;

    public Cars(final List<String> nameValues) {
        validate(nameValues);
        this.racingCars = createBy(nameValues);
    }

    private void validate(final List<String> names) {
        if (isDuplicated(names)) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 중복이 허용되지 않습니다.");
        }
        if (isSizeNotOk(names)) {
            throw new IllegalArgumentException("[ERROR] 자동차 대수는 2-50대 사이이어야 합니다.");
        }
    }

    private List<Car> createBy(final List<String> names) {
        return names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private boolean isDuplicated(final List<String> names) {
        return names.size() != names.stream().distinct().count();
    }

    private boolean isSizeNotOk(final List<String> names) {
        return !(MINIMUM_NUMBER_OF_CARS <= names.size() && names.size() <= MAXIMUM_NUMBER_OF_CARS);
    }

    public void race(final NumberGenerator numberGenerator) {
        racingCars.forEach(car -> car.move(numberGenerator));
    }

    public List<Car> getRacingCars() {
        return new ArrayList<>(racingCars);
    }
}
