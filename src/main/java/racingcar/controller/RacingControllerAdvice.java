package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponseDto;

@RestControllerAdvice(assignableTypes = {RacingController.class})
public class RacingControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto sendExceptionMessage(IllegalArgumentException ex) {
        return new ExceptionResponseDto(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseDto sendDefaultExceptionMessage(RuntimeException e) {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.error("{} : {}", e.getClass(), e.getMessage(), e);
        return new ExceptionResponseDto("서버가 응답하지 않습니다.");
    }
}
