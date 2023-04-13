package racingcar.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.ExceptionMessageDTO;

@ControllerAdvice
public class WebControllerAdvice {
    @ResponseBody
    @ExceptionHandler
    public ExceptionMessageDTO sendExceptionMessage(IllegalArgumentException ex) {
        return new ExceptionMessageDTO(ex.getMessage());
    }
}
