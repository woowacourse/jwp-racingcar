package racingcar.exception;

import org.springframework.http.HttpStatus;

public class RacingCarException extends IllegalArgumentException{
    private final HttpStatus status;

    public RacingCarException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
