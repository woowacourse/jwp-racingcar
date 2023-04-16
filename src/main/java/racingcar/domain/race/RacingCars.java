package racingcar.domain.race;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

public class RacingCars {
    private final List<Car> cars;

    public RacingCars(List<Car> cars) {
        validate(cars);
        this.cars = cars;
    }

    private void validate(List<Car> cars) {
        if (hasSameName(cars)) {
            throw new IllegalArgumentException("자동차의 이름은 중복일 수 없습니다.");
        }
    }

    private boolean hasSameName(List<Car> cars) {
        long distinctNameCount = cars.stream()
                .map(Car::getName)
                .distinct()
                .count();
        return cars.size() != distinctNameCount;
    }

    public void moveCars(List<Integer> numbers) {
        for (int index = 0; index < cars.size(); index++) {
            cars.get(index).moveDependingOn(numbers.get(index));
        }
    }

    public int calculateMaxPosition() {
        return cars.stream().mapToInt(Car::getPosition).max().orElse(0);
    }

    public List<Car> filter(Predicate<Car> predicate) {
        return cars.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
