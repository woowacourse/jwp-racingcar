package racingcar.exception;

import org.springframework.http.HttpStatus;

public class RacingCarException extends IllegalArgumentException {

    private final HttpStatus httpStatus;

    public RacingCarException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
