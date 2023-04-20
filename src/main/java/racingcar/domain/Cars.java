package racingcar.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Cars {
    private static final int MAX_SIZE = 20;
    private static final String NAME_DELIMITER = ",";

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        validateCars(cars);
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        validateDuplicatedName(carNames);
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    public static Cars from(final String carNames) {
        List<String> seperatedCarNames = Arrays.stream(carNames.split(NAME_DELIMITER))
                .collect(Collectors.toList());
        return from(seperatedCarNames);
    }

    private static void validateDuplicatedName(final List<String> cars) {
        Set<String> refined = new HashSet<>(cars);

        if (refined.size() != cars.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private void validateCars(final List<Car> cars) {
        validateEmpty(cars);
        validateSize(cars);
    }

    private void validateEmpty(final List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("최소 하나 이상의 Car 객체가 존재해야합니다.");
        }
    }

    private void validateSize(final List<Car> cars) {
        if (cars.size() > MAX_SIZE) {
            throw new IllegalArgumentException("경주 게임을 진행할 자동차는 최대 20개까지 생성할 수 있습니다.");
        }
    }

    public void moveAll(final RandomNumberGenerator generator) {
        for (Car car : cars) {
            int power = generator.generate();
            car.move(power);
        }
    }

    public List<Car> getMaxPositionCars() {
        int maxPosition = this.calculateMaxPosition();

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private int calculateMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("하나 이상의 자동차가 있어야합니다."));
    }

    public String getCombinedNames() {
        List<String> names = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        return String.join(NAME_DELIMITER, names);
    }

    public List<Car> getUnmodifiableCars() {
        return Collections.unmodifiableList(cars);
    }
}
