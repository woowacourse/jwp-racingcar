package racingcar.common.exception;

import racingcar.common.ErrorData;

public class ResourceLengthException extends RuntimeException {

    private final ErrorData<Integer> length;

    public ResourceLengthException(final ErrorData<Integer> length) {
        this.length = length;
    }

    public ErrorData<Integer> getLength() {
        return length;
    }
}
