package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.CustomException;
import racingcar.exception.ExceptionInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RacingWebAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionInfo> customHandler(final CustomException customException) {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(customException.getErrorMessage());
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
            final MethodArgumentNotValidException exception) {
        final List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }
}
