package racingcar.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cars {

    private static final String DUPLICATE_MESSAGE = "중복된 값을 입력할 수 없습니다.";
    private static final String NO_RESOURCE_MESSAGE = "%s(이)가 존재하지 않습니다.";

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;

    public Cars(final List<Car> cars, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
        validateDuplicateCarName();
    }

    public Cars race() {
        final List<Car> movedCars = cars.stream()
            .map(car -> {
                final int power = numberGenerator.generate();
                return car.move(power);
            })
            .collect(Collectors.toUnmodifiableList());
        return new Cars(movedCars, numberGenerator);
    }

    public List<String> getWinnerCarNames() {
        final Car maxPositionCar = judgeMaxPositionCar();
        return cars.stream()
            .filter(maxPositionCar::isSamePosition)
            .map(car -> car.getCarName().getName())
            .collect(Collectors.toUnmodifiableList());
    }

    private Car judgeMaxPositionCar() {
        return cars.stream()
            .max(Car::compareTo)
            .orElseThrow(
                () -> new IllegalArgumentException(String.format(NO_RESOURCE_MESSAGE, "차량 리스트")));
    }

    private void validateDuplicateCarName() {
        final int uniqueCarCount = new HashSet<>(cars).size();
        if (cars.size() != uniqueCarCount) {
            throw new IllegalArgumentException(DUPLICATE_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object diffCars) {
        if (this == diffCars) {
            return true;
        }
        if (diffCars == null || getClass() != diffCars.getClass()) {
            return false;
        }
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
