package racingcar.controller;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import racingcar.dto.ExceptionResponse;

@RestControllerAdvice
public class RacingCarControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
            final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse<>("유효하지 않은 요청입니다."));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
            final HttpStatus status, final WebRequest request) {
        return ResponseEntity.badRequest().body(new ExceptionResponse<>("올바른 타입을 입력해주세요."));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return ResponseEntity.badRequest().body(new ExceptionResponse<>(fieldErrors));
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleException() {
        return ResponseEntity.internalServerError().body(new ExceptionResponse<>("예상치 못한 예외가 발생했습니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<Object> handleIllegalArgument(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse<>(e.getMessage()));
    }
}
