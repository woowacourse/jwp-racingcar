package racingcar.domain;

public class RacingGame {

    private final RacingCars racingCars;
    private final TryCount tryCount;

    public RacingGame(RacingCars racingCars, TryCount tryCount) {
        this.racingCars = racingCars;
        this.tryCount = tryCount;
    }

    public RacingCars moveCars() {
        while (canProceed()) {
            racingCars.moveAll();
            tryCount.deduct();
        }
        return racingCars;
    }

    private boolean canProceed() {
        return !tryCount.isZero();
    }
}
