package racingcar.domain;

import racingcar.RandomNumberGenerator;
import racingcar.api.dto.request.CarGameRequest;

import java.util.*;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        List<String> refinedNames = carNames.stream()
                .map(String::strip)
                .collect(Collectors.toList());

        validateCarNames(refinedNames);

        return new Cars(refinedNames.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
    }

    public static Cars from(final CarGameRequest request) {
        List<String> carNames = Arrays.stream(request.getNames().split(",", -1))
                .map(String::strip)
                .collect(Collectors.toList());

        validateCarNames(carNames);

        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    private static void validateCarNames(final List<String> cars) {
        validateEmpty(cars);
        validateDuplicatedName(cars);
    }

    private static void validateEmpty(final List<String> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("최소 하나 이상의 Car 객체가 존재해야합니다.");
        }
    }

    private static void validateDuplicatedName(final List<String> cars) {
        Set<String> refined = new HashSet<>(cars);

        if (refined.size() != cars.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    public void moveAll(final RandomNumberGenerator generator) {
        for (Car car : cars) {
            int power = generator.generate();
            car.move(power);
        }
    }

    public List<Car> decideWinners() {
        int maxPosition = this.calculateMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toUnmodifiableList());
    }

    private int calculateMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(Car.MIN_POSITION);
    }

    public List<Car> getUnmodifiableCars() {
        return Collections.unmodifiableList(cars);
    }
}
