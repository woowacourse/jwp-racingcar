package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RacingGameControllerAdvice {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(final IllegalArgumentException e) {
        log.error(e.getMessage(), e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handle(final Exception e) {
        log.error(e.getMessage(), e);
    }
}
