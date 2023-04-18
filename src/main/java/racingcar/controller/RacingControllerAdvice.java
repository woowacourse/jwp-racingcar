package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponseDto;

@RestControllerAdvice(basePackages = {"racingcar.controller"})
public class RacingControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto sendExceptionMessage(IllegalArgumentException ex) {
        return new ExceptionResponseDto(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseDto sendDefaultExceptionMessage() {
        return new ExceptionResponseDto("서버가 응답하지 않습니다.");
    }
}
