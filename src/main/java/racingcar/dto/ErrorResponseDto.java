package racingcar.dto;

public class ErrorResponseDto {
    private final int status;
    private final String message;
    private final long timestamp;

    public ErrorResponseDto(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
