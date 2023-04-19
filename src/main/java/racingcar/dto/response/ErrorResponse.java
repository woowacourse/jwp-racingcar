package racingcar.dto.response;

public class ErrorResponse {

    private final int code;
    private final String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse send(final int code, final String message) {
        return new ErrorResponse(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
