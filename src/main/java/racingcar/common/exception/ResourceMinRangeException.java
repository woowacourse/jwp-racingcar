package racingcar.common.exception;

import racingcar.common.ErrorData;

public class ResourceMinRangeException extends RuntimeException {

    private final ErrorData<Integer> range;

    public ResourceMinRangeException(final ErrorData<Integer> range) {
        this.range = range;
    }

    public ErrorData<Integer> getRange() {
        return range;
    }
}
