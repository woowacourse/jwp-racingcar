package racingcar.dto.response;

public class BadResponseDto {
    private final int status;
    private final String message;

    public BadResponseDto(int status, String message) {
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
