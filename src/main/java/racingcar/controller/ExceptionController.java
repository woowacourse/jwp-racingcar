package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.PlayFailResponse;

@RestControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<PlayFailResponse> handle(Exception exception) {
        log.error(exception.getMessage());
        PlayFailResponse playFailResponse = new PlayFailResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(playFailResponse);
    }
}
