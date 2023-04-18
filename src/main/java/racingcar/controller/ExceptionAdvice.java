package racingcar.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException() {
        return ResponseEntity.badRequest().body("DataAccessException");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException() {
        return ResponseEntity.badRequest().body("IllegalArgumentException");
    }
}
