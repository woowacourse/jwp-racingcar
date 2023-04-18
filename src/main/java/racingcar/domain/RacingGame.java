package racingcar.domain;

import java.util.List;
import racingcar.strategy.MovingStrategy;

public class RacingGame {

    private final int id;
    private final Cars cars;
    private final MovingStrategy movingStrategy;

    public RacingGame(int id, Cars cars, MovingStrategy movingStrategy) {
        this.id = id;
        this.cars = cars;
        this.movingStrategy = movingStrategy;
    }

    public void race(final int tryCount) {
        for (int i = 0; i < tryCount; i++) {
            cars.moveCars(movingStrategy);
        }
    }

    public List<Car> getWinners() {
        return cars.findWinners();
    }

    public int getId() {
        return id;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
