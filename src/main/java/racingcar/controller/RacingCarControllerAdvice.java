package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import racingcar.dto.ExceptionResponse;

@RestControllerAdvice
public class RacingCarControllerAdvice {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionResponse> exceptionHandler() {
        return ResponseEntity.internalServerError().body(new ExceptionResponse("예상치 못한 예외가 발생했습니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ExceptionResponse> illegalArgumentExceptionHandler(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ExceptionResponse> methodArgumentTypeMismatchExceptionHandler() {
        return ResponseEntity.badRequest().body(new ExceptionResponse("올바른 타입을 입력해주세요."));
    }
}
