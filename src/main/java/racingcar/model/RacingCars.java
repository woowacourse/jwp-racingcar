package racingcar.model;

import racingcar.util.NumberGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RacingCars {

    private static final String EMPTY_CARS_ERROR_MESSAGE = "[ERROR] 경주할 자동차 리스트가 비었습니다.";

    private final List<Car> cars;

    public RacingCars(List<Car> cars) {
        this.cars = cars;
    }

    public void moveAllCars(final int trialCount, final NumberGenerator numberGenerator) {
        for (int repeat = 0; repeat < trialCount; repeat++) {
            moveCar(numberGenerator);
        }
    }

    private void moveCar(final NumberGenerator numberGenerator) {
        for (Car car : cars) {
            int randomValue = numberGenerator.generate();
            car.move(car.canMoving(randomValue));
        }
    }

    public List<Car> getWinners() {
        Car maxPositionCar = getMaxPositionCar();
        return getMaxPositionCars(maxPositionCar);
    }

    private Car getMaxPositionCar() {
        return cars.stream()
                   .max(Car::compareTo)
                   .orElseThrow(() -> new IllegalStateException(EMPTY_CARS_ERROR_MESSAGE));
    }

    private List<Car> getMaxPositionCars(Car maxPositionCar) {
        return cars.stream()
                   .filter(car -> car.isSamePositionCar(maxPositionCar))
                   .collect(Collectors.toUnmodifiableList());
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
