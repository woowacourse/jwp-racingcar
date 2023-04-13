package racingcar.exception;

public class CustomException extends IllegalArgumentException {
    private int errorNumber;

    public CustomException(final int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public int getErrorNumber() {
        return errorNumber;
    }
}
