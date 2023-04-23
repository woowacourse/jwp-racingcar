package racingcar.global.exception.response;

public class DefaultExceptionResponse {

    private final String message;

    private DefaultExceptionResponse(final String message) {
        this.message = message;
    }

    public static DefaultExceptionResponse from(Exception exception) {
        return new DefaultExceptionResponse(exception.getMessage());
    }

    public String getMessage() {
        return message;
    }
}
