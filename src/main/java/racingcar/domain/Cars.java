package racingcar.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private static final int CARS_MIN_SIZE = 2;
    private static final String CARS_SIZE_ERROR = "자동차 대수는 2 이상이어야 합니다.";

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validate(cars);
        this.cars = new ArrayList<>(cars);
    }

    private void validate(List<Car> cars) {
        if (cars.size() < CARS_MIN_SIZE) {
            throw new IllegalArgumentException(CARS_SIZE_ERROR);
        }

        if (hasDuplicateName(cars)) {
            throw new IllegalArgumentException("중복된 자동차 이름이 존재합니다.");
        }
    }

    private boolean hasDuplicateName(List<Car> cars) {
        List<String> carNames = cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        return carNames.size() != new HashSet<>(carNames).size();
    }

    public void move(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.move(numberGenerator.generate());
        }
    }

    public List<Car> findAllWinner() {
        Car maxPositionCar = findMaxPositionCar();
        return findSamePositionCars(maxPositionCar);
    }

    private Car findMaxPositionCar() {
        return cars.stream()
                .max(Car::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Cars가 비어있습니다."));
    }

    private List<Car> findSamePositionCars(Car maxPositionCar) {
        return cars.stream()
                .filter(car -> car.isSamePosition(maxPositionCar))
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }
}
