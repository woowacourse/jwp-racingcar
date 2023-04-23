package racingcar.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.CustomException;

import java.util.HashMap;
import java.util.Map;

import static racingcar.exception.ExceptionInformation.INVALID_DATABASE_ACCESS;

@ControllerAdvice
public class RacingGameExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDatabaseException(final CustomException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(exceptionResponse);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleDatabaseException(final DataAccessException exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", INVALID_DATABASE_ACCESS.getExceptionMessage());
        return ResponseEntity.status(INVALID_DATABASE_ACCESS.getHttpStatus()).body(exceptionResponse);
    }
}
