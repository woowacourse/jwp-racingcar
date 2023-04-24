package racingcar.domain.car;

import racingcar.domain.strategy.MovingStrategy;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Cars {

    private static final String NAME_DELIMITER = ",";

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars createCars(final String carNames) {
        final List<String> split = Arrays.stream(carNames.split(NAME_DELIMITER))
                                         .map(String::strip)
                                         .collect(toList());
        validateNoDuplicateNames(split);

        return split.stream()
                    .map(Car::createCar)
                    .collect(collectingAndThen(toList(), Cars::new));
    }

    private static void validateNoDuplicateNames(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("이름에 중복이 있습니다");
        }
    }

    public void moveCars(MovingStrategy movingStrategy) {
        cars.forEach(car -> car.move(movingStrategy));
    }

    public List<Car> getWinningCars() {
        final Optional<Car> optionalFastestCar = cars.stream()
                                                     .max(new Car.PositionComparator());
        final Car fastestCar = optionalFastestCar.orElseThrow(() -> new IllegalStateException("자동차가 없습니다"));
        return getWinningCars(fastestCar);
    }

    private List<Car> getWinningCars(final Car fastestCar) {
        return cars.stream()
                   .filter(fastestCar::isSamePosition)
                   .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}
