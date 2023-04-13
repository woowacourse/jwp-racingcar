package racingcar.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cars {

    private static final String SPLIT_DELIMITER = ",";
    private static final String DUPLICATE_MESSAGE = "중복된 값을 입력할 수 없습니다.";
    private static final String NO_RESOURCE_MESSAGE = "%s(이)가 존재하지 않습니다.";

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;

    private Cars(final String carNames, final NumberGenerator numberGenerator) {
        this.cars = create(carNames);
        this.numberGenerator = numberGenerator;
        validateDuplicateCarName();
    }

    public static Cars create(final String carNames, final NumberGenerator numberGenerator) {
        return new Cars(carNames, numberGenerator);
    }

    public void race() {
        cars.forEach(car -> {
            final int power = numberGenerator.generate();
            car.move(power);
        });
    }

    public List<String> getWinnerCarNames() {
        final Car maxPositionCar = judgeMaxPositionCar();
        return cars
                .stream()
                .filter(maxPositionCar::isSamePosition)
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private Car judgeMaxPositionCar() {
        return cars.stream()
                .max(Car::compareTo)
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_RESOURCE_MESSAGE, "차량 리스트")));
    }

    private List<Car> create(final String carNames) {
        final String[] names = splitCarNames(carNames);
        return Arrays.stream(names)
                .map(Car::create)
                .collect(Collectors.toList());
    }

    private String[] splitCarNames(final String carNames) {
        return carNames.split(SPLIT_DELIMITER);
    }

    private void validateDuplicateCarName() {
        final int uniqueCarCount = new HashSet<>(cars).size();
        if (cars.size() != uniqueCarCount) {
            throw new IllegalArgumentException(DUPLICATE_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object diffCars) {
        if (this == diffCars) return true;
        if (diffCars == null || getClass() != diffCars.getClass()) return false;
        final Cars cars = (Cars) diffCars;
        return Objects.equals(this.cars, cars.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
