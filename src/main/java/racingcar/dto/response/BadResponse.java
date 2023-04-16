package racingcar.dto.response;

public class BadResponse {
    private final int status;
    private final String message;

    public BadResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
