package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import java.util.List;
import java.util.Map;
import racingcar.strategy.MovingStrategy;

public class RacingGame {

    private final Cars cars;
    private final MovingStrategy movingStrategy;

    public RacingGame(Cars cars, MovingStrategy movingStrategy) {
        this.cars = cars;
        this.movingStrategy = movingStrategy;
    }

    public Map<String, Integer> playSingleRound() {
        cars.moveCars(movingStrategy);
        return cars.getTotalStatus();
    }

    public List<String> getWinners() {
        return cars.findWinners();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
