package racingcar.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ErrorResult;
import racingcar.exception.BlankNameException;
import racingcar.exception.DuplicateCarNameException;
import racingcar.exception.WrongNameLengthException;
import racingcar.exception.WrongRoundException;

@RestControllerAdvice
public class CarControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> handleBlankName(BlankNameException exception) {
        return ErrorResult.toResponseEntity(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> handleDuplicateCarName(DuplicateCarNameException exception) {
        return ErrorResult.toResponseEntity(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> handleWrongNameLength(WrongNameLengthException exception) {
        return ErrorResult.toResponseEntity(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> handleWrongRound(WrongRoundException exception) {
        return ErrorResult.toResponseEntity(String.valueOf(HttpStatus.BAD_REQUEST.value()), exception.getMessage());
    }
}
