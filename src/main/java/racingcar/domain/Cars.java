package racingcar.domain;

import racingcar.common.exception.DuplicateResourceException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;
    private final NumberGenerator numberGenerator;

    private Cars(final List<Car> cars, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
        validateDuplicateCarName();
    }

    public static Cars create(final List<String> carNames, final NumberGenerator numberGenerator) {
        return new Cars(createCars(carNames), numberGenerator);
    }

    public void race() {
        cars.forEach(car -> {
            final int power = numberGenerator.generate();
            car.move(power);
        });
    }

    public List<String> getWinnerCarNames() {
        final CarPosition maxPosition = getMaxPosition();
        return cars
                .stream()
                .filter(car -> maxPosition.isSamePosition(car.getCarPosition()))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Car> createCars(final List<String> carNames) {
        return carNames.stream()
                .map(name -> new Car(CarName.create(name), CarPosition.init()))
                .collect(Collectors.toUnmodifiableList());
    }

    private CarPosition getMaxPosition() {
        return cars.stream()
                .map(Car::getCarPosition)
                .max(CarPosition::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void validateDuplicateCarName() {
        final List<String> carNames = getCarNames();
        final int uniqueCarCount = new HashSet<>(carNames).size();
        if (carNames.size() != uniqueCarCount) {
            throw new DuplicateResourceException();
        }
    }

    private List<String> getCarNames() {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
