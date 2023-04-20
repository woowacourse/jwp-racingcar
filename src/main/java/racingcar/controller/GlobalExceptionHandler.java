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
        return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "공백을 제외한 이름의 길이가 1이상 5자 이하이어야 합니다", System.currentTimeMillis()));
    }

    @ExceptionHandler(TryCountException.class)
    public ResponseEntity<ErrorResponseDto> handleTryCountException(TryCountException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), "시도 횟수는 0 이상의 숫자를 입력해야 합니다.", System.currentTimeMillis()));
    }
}
