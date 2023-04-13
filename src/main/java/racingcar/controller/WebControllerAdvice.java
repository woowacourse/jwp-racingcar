package racingcar.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionMessageDTO;

@RestControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler
    public ExceptionMessageDTO sendExceptionMessage(IllegalArgumentException ex) {
        return new ExceptionMessageDTO(ex.getMessage());
    }
}
