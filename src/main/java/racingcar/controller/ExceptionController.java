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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.internalServerError()
                .body("요청을 처리할 수 없습니다.");
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
