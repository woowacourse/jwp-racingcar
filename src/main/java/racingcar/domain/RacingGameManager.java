package racingcar.domain;

import racingcar.domain.engine.Engine;
import racingcar.domain.engine.RandomMovingEngine;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameManager {
    private static final RacingGameManager RACING_GAME_MANAGER = new RacingGameManager();

    private RacingGameManager() {
    }

    public static RacingGameManager getInstance() {
        return RACING_GAME_MANAGER;
    }

    public void play(Cars cars, TryCount tryCount) {
        Engine engine = new RandomMovingEngine();

        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveCars(engine);
        }
    }

    public Cars decideWinners(Cars cars) {
        int maxPosition = getMaxPosition(cars);
        List<Car> result = cars.getCars().stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());

        return Cars.from(result);
    }

    private int getMaxPosition(Cars cars) {
        return cars.getCars().stream()
                .map(Car::getPosition)
                .max(Integer::compareTo)
                .orElseThrow();
    }
}
