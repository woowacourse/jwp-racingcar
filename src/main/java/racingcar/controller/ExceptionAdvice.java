package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(final DataAccessException exception) {
        log.error("DataAccessException: " + exception.getMessage());
        return ResponseEntity.internalServerError().body("잠시 후 다시 요청해주세요");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(final IllegalArgumentException exception) {
        log.error("IllegalArgumentException: " + exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
