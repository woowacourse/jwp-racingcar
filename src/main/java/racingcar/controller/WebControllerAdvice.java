package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionMessageDTO;

@RestControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessageDTO sendExceptionMessage(IllegalArgumentException ex) {
        return new ExceptionMessageDTO(ex.getMessage());
    }
}
