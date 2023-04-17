package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponseDto;

@RestControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<ExceptionResponseDto> handle(Exception exception) {
        log.error(exception.getMessage());
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponseDto);
    }
}
