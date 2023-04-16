package racingcar.domain;

public class TryCount {

    private final int tryCount;

    public TryCount(final int tryCount) {
        this.tryCount = tryCount;
    }

    public TryCount next() {
        return new TryCount(tryCount - 1);
    }

    public boolean hasNext() {
        return tryCount > 0;
    }
}
