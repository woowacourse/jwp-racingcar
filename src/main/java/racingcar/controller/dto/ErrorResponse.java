package racingcar.controller.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp;
    private final int statusCode;

    public ErrorResponse(final String message, final LocalDateTime timestamp, final int statusCode) {
        this.message = message;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
