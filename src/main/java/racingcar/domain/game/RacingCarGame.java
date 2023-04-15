package racingcar.domain.game;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MovingStrategy;
import racingcar.domain.strategy.NumberMovingStrategy;
import racingcar.domain.strategy.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class RacingCarGame {

    private final Cars cars;

    private RacingCarGame(Cars cars) {
        this.cars = cars;
    }

    public static RacingCarGame createNewGame(String carNames) {
        return new RacingCarGame(Cars.createCars(carNames));
    }

    public void moveCars(int count) {
        for (int i = 0; i < count; i++) {
            cars.moveCars(getMovingStrategy());
        }
    }

    private MovingStrategy getMovingStrategy() {
        return new NumberMovingStrategy(new RandomNumberGenerator());
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars.getCars());
    }

    public List<Car> getWinners() {
        return cars.getWinningCars();
    }
}
