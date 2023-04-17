package racingcar.domain;

import racingcar.NumberGenerator;

public class RacingGame {
    public static final int MAX_TRY_COUNT_BOUND = 100;

    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private int tryCount;

    public RacingGame(NumberGenerator numberGenerator, int tryCount, Cars cars) {
        validateTryCount(tryCount);
        this.numberGenerator = numberGenerator;
        this.cars = cars;
        this.tryCount = tryCount;
    }

    private void validateTryCount(int tryCount) {
        if (tryCount > MAX_TRY_COUNT_BOUND) {
            throw new IllegalArgumentException("시도 횟수는 100회 이하만 가능합니다 현재 : " + tryCount + "회");
        }
    }

    public void playOneRound() {
        cars.moveAll(numberGenerator);
        tryCount--;
    }

    public boolean isEnd() {
        return tryCount == 0;
    }

    public Cars getCars() {
        return cars;
    }
}
