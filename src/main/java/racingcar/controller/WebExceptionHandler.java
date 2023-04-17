package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.response.BadResponseDto;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BadResponseDto> handleException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new BadResponseDto(e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BadResponseDto> handleInvalidRequest() {
        return ResponseEntity.badRequest().body(new BadResponseDto("입력 형식이 올바르지 않습니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BadResponseDto> handleException() {
        return ResponseEntity.badRequest().body(new BadResponseDto("예기치 못한 오류가 발생하였습니다."));
    }
}
