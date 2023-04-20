package racingcar.controller.exception;

public class ExceptionResponse {

    private final String exceptionMessage;

    public ExceptionResponse(final String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
