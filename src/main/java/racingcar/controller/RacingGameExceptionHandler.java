package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.RuntimeExceptionImpl;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RacingGameExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeExceptionImpl exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatusValue()).body(exceptionResponse);
    }
}
