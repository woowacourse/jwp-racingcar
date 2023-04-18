package racingcar.exception;

public class CustomException extends IllegalArgumentException {
    private ExceptionStatus exceptionStatus;

    public CustomException(final ExceptionStatus exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public int getErrorNumber() {
        return exceptionStatus.getValue();
    }

    public String getErrorMessage() {
        return exceptionStatus.getMessage();
    }
}
