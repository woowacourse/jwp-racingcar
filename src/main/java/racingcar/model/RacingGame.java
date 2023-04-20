package racingcar.model;

import racingcar.util.NumberGenerator;

public class RacingGame {
    private final Cars cars;
    private final int tryCount;

    public RacingGame(Cars cars, int tryCount) {
        this.cars = cars;
        this.tryCount = tryCount;
    }

    public void race(NumberGenerator numberGenerator) {
        for (int count = 0; count < tryCount; count++) {
            cars.moveResult(numberGenerator);
        }
    }
}
