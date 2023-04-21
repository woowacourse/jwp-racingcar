package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(RuntimeException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
