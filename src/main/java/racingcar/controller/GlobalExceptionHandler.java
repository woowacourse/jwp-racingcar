package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.dto.ErrorResponseDto;
import racingcar.exception.CarNameLengthException;
import racingcar.exception.TryCountException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarNameLengthException.class)
    public ResponseEntity<ErrorResponseDto> handleCarNameException(CarNameLengthException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "이름 에러 발생", System.currentTimeMillis()));
    }

    @ExceptionHandler(TryCountException.class)
    public ResponseEntity<ErrorResponseDto> handleTryCountException(TryCountException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "시도 횟수 에러 발생", System.currentTimeMillis()));
    }
}
