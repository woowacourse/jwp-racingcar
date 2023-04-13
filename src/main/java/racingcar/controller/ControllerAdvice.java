package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionMessageDTO;

@RestControllerAdvice(assignableTypes = {RacingController.class})
public class ControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessageDTO sendExceptionMessage(IllegalArgumentException ex) {
        return new ExceptionMessageDTO(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessageDTO sendDefaultExceptionMessage() {
        return new ExceptionMessageDTO("서버가 응답하지 않습니다.");
    }
}
