package racingcar.domain;

public class RacingGame {
    public static final int MAX_TRY_COUNT_BOUND = 100;

    private final MoveStrategy moveStrategy;
    private final Cars cars;
    private int tryCount;

    public RacingGame(MoveStrategy moveStrategy, int tryCount, Cars cars) {
        validateTryCount(tryCount);
        this.moveStrategy = moveStrategy;
        this.cars = cars;
        this.tryCount = tryCount;
    }

    private void validateTryCount(int tryCount) {
        if (tryCount > MAX_TRY_COUNT_BOUND) {
            throw new IllegalArgumentException("시도 횟수는 100회 이하만 가능합니다 현재 : " + tryCount + "회");
        }
    }

    public void playOneRound() {
        cars.moveAll(moveStrategy);
        tryCount--;
    }

    public boolean isEnd() {
        return tryCount == 0;
    }

    public Cars getCars() {
        return cars;
    }
}
