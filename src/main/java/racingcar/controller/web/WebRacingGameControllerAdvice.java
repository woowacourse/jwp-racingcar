package racingcar.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.ExceptionResponse;

import java.util.stream.Collectors;

@RestControllerAdvice(basePackageClasses = WebRacingGameController.class)
public class WebRacingGameControllerAdvice {
    private static final String FIELD_DELIMITER = ",";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidException(final MethodArgumentNotValidException exception) {
        String fieldNames = exception.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(FIELD_DELIMITER));
        return ResponseEntity.badRequest().body(new ExceptionResponse(fieldNames + "을 입력해주세요."));
    }
}
