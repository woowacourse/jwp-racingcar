package racingcar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RacingCarExceptionHandler {

    @ExceptionHandler(CarException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String carExceptionHandler(final CarException carException) {
        return carException.getMessage();
    }

    @ExceptionHandler(CarsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String carsExceptionHandler(final CarsException carsException) {
        return carsException.getMessage();
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(final RuntimeException exception) {
        return exception.getMessage();
    }
}
