package racingcar.exception;

public class NotExistCarsException extends CustomException {
    private static final int ERROR_NUMBER = 106;

    public NotExistCarsException(String message) {
        super(ERROR_NUMBER, message);
    }
}
