package racingcar.domain.race;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;
import racingcar.domain.car.Position;

public class RacingCars {
    private final List<Car> cars;

    public RacingCars(List<String> carNames) {
        validate(carNames);
        cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void validate(List<String> carNames) {
        if (hasSameName(carNames)) {
            throw new IllegalArgumentException("자동차의 이름은 중복일 수 없습니다.");
        }
    }

    private boolean hasSameName(List<String> carNames) {
        long distinctNameCount = carNames.stream()
                .distinct()
                .count();
        return carNames.size() != distinctNameCount;
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void moveCars() {
        for (Car car : cars) {
            car.moveDependingOn();
        }
    }

    public Position findBestPosition() {
        int maxValue = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
        return new Position(maxValue);
    }

    public List<Car> findCarsInSamePosition(Position maxPosition) {
        return cars.stream()
                .filter(car -> car.isInSamePosition(maxPosition))
                .collect(Collectors.toList());
    }
}
