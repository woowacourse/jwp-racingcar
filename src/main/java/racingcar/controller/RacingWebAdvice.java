package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.CustomException;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.HasBlankCarNameException;
import racingcar.exception.InvalidCarNameFormatException;
import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.exception.InvalidTrialTimesFormatException;

@RestControllerAdvice
public class RacingWebAdvice {

    @ExceptionHandler(DuplicateCarNamesException.class)
    public ResponseEntity<CustomException> duplicateCarNamesHandler() {
        return ResponseEntity.badRequest().body(new DuplicateCarNamesException());
    }

    @ExceptionHandler(ExceedCarNameLengthException.class)
    public ResponseEntity<CustomException> exceedCarNameLengthHandler() {
        return ResponseEntity.badRequest().body(new ExceedCarNameLengthException());
    }

    @ExceptionHandler(InvalidCarNameFormatException.class)
    public ResponseEntity<CustomException> invalidCarNameFormatHandler() {
        return ResponseEntity.badRequest().body(new InvalidCarNameFormatException());
    }

    @ExceptionHandler(InvalidRangeTrialTimesException.class)
    public ResponseEntity<CustomException> invalidRangeTrialTimesHandler() {
        return ResponseEntity.badRequest().body(new InvalidRangeTrialTimesException());
    }

    @ExceptionHandler(InvalidTrialTimesFormatException.class)
    public ResponseEntity<CustomException> invalidTrialTimesFormatHandler() {
        return ResponseEntity.badRequest().body(new InvalidTrialTimesFormatException());
    }

    @ExceptionHandler(HasBlankCarNameException.class)
    public ResponseEntity<CustomException> hasBlankCarNameExceptionHandler() {
        return ResponseEntity.badRequest().body(new HasBlankCarNameException());
    }
}
