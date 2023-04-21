package racingcar.controller;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import racingcar.dto.ExceptionResponse;

@RestControllerAdvice
public class RacingCarControllerAdvice extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    private ResponseEntity<ExceptionResponse<String>> handleException() {
        return ResponseEntity.internalServerError().body(new ExceptionResponse<>("예상치 못한 예외가 발생했습니다."));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
            final WebRequest request) {
        final Map<String, String> body = ex.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ExceptionResponse<String>> handleIllegalArgumentException(
            final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse<>(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ExceptionResponse<String>> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse<>("올바른 타입을 입력해주세요."));
    }
}
