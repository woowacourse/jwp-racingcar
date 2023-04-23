package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpringControllerAdvice {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<String> handle(Exception ex) {
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
