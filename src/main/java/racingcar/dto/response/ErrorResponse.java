package racingcar.dto.response;

public class ErrorResponse {

    private final String message;

    public ErrorResponse(final String message) {
        this.message = message;
    }

    public static ErrorResponse send(final String message) {
        return new ErrorResponse(message);
    }

    public String getMessage() {
        return message;
    }
}
