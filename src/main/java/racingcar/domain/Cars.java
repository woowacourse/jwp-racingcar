package racingcar.domain;

import racingcar.exception.BadRequestException;
import racingcar.strategy.RacingNumberGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<String> carNames) {
        checkDuplication(carNames);
        final List<Car> cars = generateCars(carNames);

        return new Cars(cars);
    }

    public void race(RacingNumberGenerator generator) {
        cars.forEach(car -> car.race(generator));
    }

    public String findWinnerNames() {
        final Car winner = findWinner();

        return cars.stream()
                .filter(car -> car.isSamePosition(winner))
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private static void checkDuplication(final List<String> carNames) {
        final int uniqueCarNameCount = new HashSet<>(carNames).size();
        if (uniqueCarNameCount != carNames.size()) {
            throw new BadRequestException("이름은 중복될 수 없습니다.");
        }
    }

    private static List<Car> generateCars(final List<String> carNames) {
        return carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    private Car findWinner() {
        return cars.stream()
                .max(Car::compareTo)
                .orElse(null);
    }

    public List<Car> getCars() {
        return cars;
    }

}
