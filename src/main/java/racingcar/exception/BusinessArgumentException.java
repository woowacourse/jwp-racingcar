package racingcar.exception;

public class BusinessArgumentException extends IllegalArgumentException {
    private final ErrorCode errorCode;

    public BusinessArgumentException(ErrorCode errorCode) {
        this.errorCode = errorCode;

    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public int getErrorCodeStatus() {
        return errorCode.getStatus();
    }
}
