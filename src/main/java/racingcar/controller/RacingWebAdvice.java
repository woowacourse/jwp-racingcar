package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.DuplicateCarNamesException;
import racingcar.exception.ExceedCarNameLengthException;
import racingcar.exception.InvalidCarNameFormatException;

@ControllerAdvice
public class RacingWebAdvice {

    @ExceptionHandler(DuplicateCarNamesException.class)
    public ResponseEntity<String> duplicateCarNamesHandler() {
        System.out.println("RacingWebAdvice.duplicateCarNamesHandler");
        return ResponseEntity.badRequest().body("DuplicateCarNamesException");
    }

    @ExceptionHandler(ExceedCarNameLengthException.class)
    public ResponseEntity<String> exceedCarNameLengthHandler() {
        System.out.println("RacingWebAdvice.exceedCarNameLengthHandler");
        return ResponseEntity.badRequest().body("ExceedCarNameLengthException");
    }

    @ExceptionHandler(InvalidCarNameFormatException.class)
    public ResponseEntity<String> invalidCarNameFormatHandler() {
        System.out.println("RacingWebAdvice.invalidCarNameFormatHandler");
        return ResponseEntity.badRequest().body("InvalidCarNameFormatException");
    }

}
