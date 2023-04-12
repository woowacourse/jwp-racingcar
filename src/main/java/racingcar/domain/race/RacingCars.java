package racingcar.domain.race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

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
        carNames.forEach(System.out::println);
        long distinctNameCount = carNames.stream()
                .distinct()
                .count();
        System.out.println("distinctNameCount = " + distinctNameCount);
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
}
