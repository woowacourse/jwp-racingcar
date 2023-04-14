package racingcar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CarsException extends RuntimeException {

    public CarsException(final String message) {
        super(message);
    }
}
