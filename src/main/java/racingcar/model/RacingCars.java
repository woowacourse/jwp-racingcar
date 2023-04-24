package racingcar.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {

    private final List<Car> cars;

    public RacingCars(final List<Car> cars) {
        this.cars = cars;
    }

    public void tryOneTime(final CarNumberGenerator carNumberGenerator) {
        for (final Car car : cars) {
            final int randomValue = carNumberGenerator.generate();
            car.move(car.canMoving(randomValue));
        }
    }

    public List<Car> getWinners() {
        final Car maxPositionCar = getMaxPositionCar();
        return getMaxPositionCars(maxPositionCar);
    }

    private Car getMaxPositionCar() {
        Car maxPositionCar = cars.get(0);
        for (final Car car : cars) {
            maxPositionCar = car.getLargerCar(maxPositionCar);
        }
        return maxPositionCar;
    }

    private List<Car> getMaxPositionCars(final Car maxPositionCar) {
        return cars.stream()
                .filter(car -> car.isSamePositionCar(maxPositionCar))
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
