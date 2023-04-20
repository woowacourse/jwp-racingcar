package racingcar.dto;

public class ExceptionResponse {

    private final boolean success = false;
    private final String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
