package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(new ExceptionMessage(e));
    }

    private static class ExceptionMessage {
        private final String message;

        public ExceptionMessage(Exception e) {
            this.message = e.getMessage();
        }

        public String getMessage() {
            return message;
        }
    }
}
