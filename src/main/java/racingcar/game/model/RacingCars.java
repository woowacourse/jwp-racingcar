package racingcar.game.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.model.Car;
import racingcar.car.model.CarNumberGenerator;

public class RacingCars {
    
    private final List<Car> cars;
    
    public RacingCars(final List<Car> cars) {
        this.cars = cars;
    }
    
    public void tryOneTime(final CarNumberGenerator carNumberGenerator) {
        for (final Car car : this.cars) {
            final int randomValue = carNumberGenerator.generate();
            car.move(car.canMoving(randomValue));
        }
    }
    
    public List<Car> getWinners() {
        final Car maxPositionCar = this.getMaxPositionCar();
        return this.getMaxPositionCars(maxPositionCar);
    }
    
    private Car getMaxPositionCar() {
        Car maxPositionCar = this.cars.get(0);
        for (final Car car : this.cars) {
            maxPositionCar = car.getLargerCar(maxPositionCar);
        }
        return maxPositionCar;
    }
    
    private List<Car> getMaxPositionCars(final Car maxPositionCar) {
        final List<Car> winners = this.cars.stream()
                .filter(car -> car.isSamePositionCar(maxPositionCar))
                .collect(Collectors.toList());
        return winners;
    }
    
    public List<Car> getCars() {
        return Collections.unmodifiableList(this.cars);
    }
}
