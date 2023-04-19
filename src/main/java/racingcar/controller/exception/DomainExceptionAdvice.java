package racingcar.controller.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;
import racingcar.exception.CarNameBlankException;
import racingcar.exception.CarNameLengthException;
import racingcar.exception.NoCarsExistException;
import racingcar.exception.PositionInvalidException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class DomainExceptionAdvice {

    @ExceptionHandler({
            CarNameBlankException.class,
            CarNameLengthException.class,
            NoCarsExistException.class,
            PositionInvalidException.class
    })
    public ResponseEntity<ExceptionResponse> handledException(final RuntimeException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
