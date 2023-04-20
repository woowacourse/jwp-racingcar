package racingcar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;

@RestControllerAdvice
public class Advice {

    @ExceptionHandler
    public ResponseEntity handleException(Exception exception) {
        return ResponseEntity.badRequest()
            .body(new ExceptionResponse(exception.getMessage()));
    }

}
