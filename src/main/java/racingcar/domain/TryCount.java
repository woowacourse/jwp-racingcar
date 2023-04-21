package racingcar.domain;

import racingcar.exception.ExceptionInformation;

public class TryCount {

    private static final int END_FLAG = 0;

    private int tryCount;

    public TryCount(final int tryCount) {
        validateTryCount(tryCount);

        this.tryCount = tryCount;
    }

    private void validateTryCount(final int inputCount) {
        if (inputCount <= END_FLAG) {
            throw new IllegalArgumentException(ExceptionInformation.NOT_POSITIVE_INTEGER.getExceptionMessage());
        }
    }

    public void deduct() {
        if (isZero()) {
            throw new IllegalArgumentException(ExceptionInformation.NOT_POSITIVE_INTEGER.getExceptionMessage());
        }

        tryCount--;
    }

    public boolean isZero() {
        return this.tryCount == END_FLAG;
    }
}
