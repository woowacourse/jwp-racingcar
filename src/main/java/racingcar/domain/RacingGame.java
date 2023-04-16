package racingcar.domain;

import racingcar.domain.engine.Engine;
import racingcar.domain.engine.RandomMovingEngine;

import java.util.List;

public class RacingGame {
    private final Cars cars;

    public RacingGame(List<Name> carNames) {
        Engine engine = new RandomMovingEngine();
        this.cars = new Cars(carNames, engine);
    }

    public void moveCars(TryCount tryCount) {
        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveCars();
        }
    }

    public Cars decideWinners() {
        return cars.getWinners();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
