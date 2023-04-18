package racingcar.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {

    private static final int MAX_SIZE = 20;
    private final List<Car> cars;

    public Cars(final List<String> cars) {
        validateCars(cars);
        validateSize(cars);
        this.cars = cars.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private void validateCars(final List<String> cars) {

        validateDuplicatedName(cars);
        validateEmpty(cars);
    }

    private void validateEmpty(final List<String> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException("최소 하나 이상의 Car 객체가 존재해야합니다.");
        }
    }

    private void validateDuplicatedName(final List<String> cars) {
        Set<String> refined = new HashSet<>(cars);

        if (refined.size() != cars.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    private void validateSize(final List<String> cars) {
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

    public List<Car> decideWinners() {
        int maxPosition = this.calculateMaxPosition();

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
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
