package racingcar.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.ExceptionInformation;

import java.util.HashMap;
import java.util.Map;

import static racingcar.exception.ExceptionInformation.DATABASE_ACCESS_EXCEPTION;

@ControllerAdvice
public class RacingGameExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDatabaseException(IllegalArgumentException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        ExceptionInformation exceptionInformation = ExceptionInformation.findByMessage(exception.getMessage());
        exceptionResponse.put("exception", exceptionInformation.getExceptionMessage());
        return ResponseEntity.status(exceptionInformation.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDatabaseException(DataAccessException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", DATABASE_ACCESS_EXCEPTION.getExceptionMessage());
        return ResponseEntity.status(DATABASE_ACCESS_EXCEPTION.getHttpStatus()).body(exceptionResponse);
    }
}
