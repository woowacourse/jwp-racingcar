package racingcar.domain;

import racingcar.common.exception.DuplicateResourceException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

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
                }).collect(Collectors.toUnmodifiableList());
        return new Cars(movedCars, numberGenerator);
    }

    public List<String> getWinnerCarNames() {
        final CarPosition maxPosition = judgeMaxPosition();
        return cars
                .stream()
                .filter(car -> maxPosition.isSamePosition(car.getCarPosition()))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private CarPosition judgeMaxPosition() {
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
