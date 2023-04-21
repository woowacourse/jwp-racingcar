package racingcar.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.CustomException;

@RestControllerAdvice
public class RacingWebAdvice {

    private final Logger logger = LoggerFactory.getLogger(RacingWebAdvice.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionInfo> customExceptionHandler(CustomException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> methodArgs(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        logger.error(error.getDefaultMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(error.getDefaultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionInfo> unExpectedException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.internalServerError().body(new ExceptionInfo(e.getMessage()));
    }

    static class ExceptionInfo {
        private final String message;

        public ExceptionInfo(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
