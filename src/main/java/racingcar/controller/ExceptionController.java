package racingcar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;
import racingcar.exception.CarNameBlankException;
import racingcar.exception.CarNameLengthException;
import racingcar.exception.NoCarsExistException;
import racingcar.exception.PositionInvalidException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({
            CarNameBlankException.class,
            CarNameLengthException.class,
            NoCarsExistException.class,
            PositionInvalidException.class
    })
    public ResponseEntity<ExceptionResponse> handle(RuntimeException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
