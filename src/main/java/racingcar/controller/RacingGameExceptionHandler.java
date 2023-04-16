package racingcar.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.RuntimeExceptionImpl;

@ControllerAdvice
public class RacingGameExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeExceptionImpl exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDataAccessException(DataAccessException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", exception.getMessage());
        return ResponseEntity.internalServerError().body(exceptionResponse);
    }
}
