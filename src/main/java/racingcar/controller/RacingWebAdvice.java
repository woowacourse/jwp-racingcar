package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;

@ControllerAdvice
public class RacingWebAdvice {

    @ExceptionHandler(DuplicateCarNamesException.class)
    public ResponseEntity<String> duplicateCarNamesHandler() {
        return ResponseEntity.badRequest().body("DuplicateCarNamesException");
    }

    @ExceptionHandler(ExceedCarNameLengthException.class)
    public ResponseEntity<String> exceedCarNameLengthHandler() {
        return ResponseEntity.badRequest().body("ExceedCarNameLengthException");
    }

    @ExceptionHandler(InvalidCarNameFormatException.class)
    public ResponseEntity<String> invalidCarNameFormatHandler() {
        return ResponseEntity.badRequest().body("InvalidCarNameFormatException");
    }

    @ExceptionHandler(InvalidRangeTrialTimesException.class)
    public ResponseEntity<String> invalidRangeTrialTimesHandler() {
        return ResponseEntity.badRequest().body("InvalidRangeTrialTimesException");
    }

    @ExceptionHandler(InvalidTrialTimesFormatException.class)
    public ResponseEntity<String> InvalidTrialTimesFormatHandler() {
        return ResponseEntity.badRequest().body("InvalidTrialTimesFormatException");
    }
}
