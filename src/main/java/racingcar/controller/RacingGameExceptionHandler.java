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

    @ExceptionHandler(value = {RuntimeExceptionImpl.class, DataAccessException.class})
    public ResponseEntity<Map<String, String>> handle(Exception exception) {
        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
