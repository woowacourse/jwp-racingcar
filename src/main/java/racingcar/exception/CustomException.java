package racingcar.exception;

public class CustomException extends IllegalArgumentException {
    private final int errorNumber;
    private final String message;

    public CustomException(final int errorNumber, final String message) {
        this.errorNumber = errorNumber;
        this.message = message;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public String getMessage() {
        return message;
    }
}
