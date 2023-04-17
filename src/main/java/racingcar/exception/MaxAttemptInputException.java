package racingcar.exception;

public class MaxAttemptInputException extends CustomException {

    private static final int ERROR_NUMBER = 107;

    public MaxAttemptInputException() {
        super(ERROR_NUMBER);
    }
}
