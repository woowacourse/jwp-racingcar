package racingcar.domain;

import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars() {
        cars = new ArrayList<>();
    }

    public Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public static Cars of(List<String> carNames) {
        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName));
        }

        return new Cars(cars);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void moveAll(NumberGenerator numberGenerator) {
        for (Car car : cars) {
            car.move(numberGenerator.generate());
        }
    }

    public List<Car> getWinners() {
        Car maxPositionCar = findMaxPositionCar();
        return findSamePositionCar(maxPositionCar);
    }

    private List<Car> findSamePositionCar(Car car) {
        return cars.stream()
                .filter(it -> it.equalsPosition(car))
                .collect(Collectors.toUnmodifiableList());
    }

    private Car findMaxPositionCar() {
        return cars.stream()
                .max(Car::compareTo)
                .orElseThrow();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
