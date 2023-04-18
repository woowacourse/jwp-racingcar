package racingcar.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;

@RestControllerAdvice
public class RacingWebAdvice {

    private final Logger logger = LoggerFactory.getLogger(RacingWebAdvice.class);

    @ExceptionHandler(DuplicateCarNamesException.class)
    public ResponseEntity<ExceptionInfo> duplicateCarNamesHandler(DuplicateCarNamesException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
    }

    @ExceptionHandler(ExceedCarNameLengthException.class)
    public ResponseEntity<ExceptionInfo> exceedCarNameLengthHandler(ExceedCarNameLengthException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
    }

    @ExceptionHandler(InvalidCarNameFormatException.class)
    public ResponseEntity<ExceptionInfo> invalidCarNameFormatHandler(InvalidCarNameFormatException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
    }

    @ExceptionHandler(InvalidRangeTrialTimesException.class)
    public ResponseEntity<ExceptionInfo> invalidRangeTrialTimesHandler(InvalidRangeTrialTimesException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
    }

    @ExceptionHandler(InvalidTrialTimesFormatException.class)
    public ResponseEntity<ExceptionInfo> invalidTrialTimesFormatHandler(InvalidTrialTimesFormatException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> methodArgsNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionInfo(e.getMessage()));
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
