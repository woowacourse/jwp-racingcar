package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(Exception e) {
        return ResponseEntity.internalServerError().body("예상치 못한 오류가 발생했습니다");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(RuntimeException e) {
        return ResponseEntity.internalServerError().body("예상치 못한 오류가 발생했습니다");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
