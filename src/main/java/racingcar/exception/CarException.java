package racingcar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CarException extends RuntimeException {

    public CarException(final String message) {
        super(message);
    }
}
