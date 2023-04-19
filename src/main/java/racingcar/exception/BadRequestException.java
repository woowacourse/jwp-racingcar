package racingcar.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RacingCarException {
    public BadRequestException(final String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
