package racingcar.domain;

public class TryCount {

    private static final int MINIMUM_TRY_COUNT = 2;
    private static final int MAXIMUM_TRY_COUNT = 10;

    private final int tryCount;

    public TryCount(final int tryCount) {
        this.tryCount = tryCount;
    }

    public int getTryCount() {
        return tryCount;
    }
}
