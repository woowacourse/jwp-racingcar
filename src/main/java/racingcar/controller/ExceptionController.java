package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponseDto;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<ExceptionResponseDto> handle(Exception exception) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponseDto);
    }
}
