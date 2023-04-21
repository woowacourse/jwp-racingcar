package racingcar.domain;

import java.util.List;
import java.util.Map;
import racingcar.strategy.MovingStrategy;

public class RacingGame {

    private final int id;
    private final Cars cars;
    private final MovingStrategy movingStrategy;

    public RacingGame(final int id, final Cars cars, final MovingStrategy movingStrategy) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
