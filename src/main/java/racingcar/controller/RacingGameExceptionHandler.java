package racingcar.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.ExceptionInformation;

import java.util.HashMap;
import java.util.Map;

import static racingcar.exception.ExceptionInformation.INVALID_DATABASE_ACCESS;

@ControllerAdvice
public class RacingGameExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDatabaseException(final IllegalArgumentException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        ExceptionInformation exceptionInformation = ExceptionInformation.findByMessage(exception.getMessage());
        exceptionResponse.put("exception", exceptionInformation.getExceptionMessage());
        return ResponseEntity.status(exceptionInformation.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDatabaseException(final DataAccessException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", INVALID_DATABASE_ACCESS.getExceptionMessage());
        return ResponseEntity.status(INVALID_DATABASE_ACCESS.getHttpStatus()).body(exceptionResponse);
    }
}
