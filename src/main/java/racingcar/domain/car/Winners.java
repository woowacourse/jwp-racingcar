package racingcar.domain.car;

import java.util.List;
import racingcar.domain.race.RacingCars;

public class Winners {

    private final List<Car> cars;

    private Winners(List<Car> winners) {
        this.cars = winners;
    }

    public static Winners from(RacingCars racingCars) {
        Position maxPosition = racingCars.findBestPosition();
        List<Car> cars = racingCars.findCarsInSamePosition(maxPosition);
        return new Winners(cars);
    }

    public boolean isWinner(Car car) {
        return cars.stream()
                .anyMatch(winner -> winner.getName().equals(car.getName()));
    }

    public List<Car> getCars() {
        return cars;
    }

}
