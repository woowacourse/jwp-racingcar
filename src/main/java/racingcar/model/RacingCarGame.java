package racingcar.model;

import racingcar.util.NumberGenerator;

public class RacingCarGame {

    private final Cars cars;
    private final TryCount tryCount;
    private final NumberGenerator numberGenerator;

    public RacingCarGame(final Cars cars, final TryCount tryCount, final NumberGenerator numberGenerator) {
        this.cars = cars;
        this.tryCount = tryCount;
        this.numberGenerator = numberGenerator;
    }

    public void execute() {
        for (int count = 0; count < tryCount.getValue(); count++) {
            cars.moveResult(numberGenerator);
        }
    }
}
