package racingcar.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.controller.dto.ExceptionResponse;
import racingcar.exception.CarNameBlankException;
import racingcar.exception.CarNameLengthException;
import racingcar.exception.NoCarsExistException;
import racingcar.exception.PositionInvalidException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class DomainExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({
            CarNameBlankException.class,
            CarNameLengthException.class,
            NoCarsExistException.class,
            PositionInvalidException.class
    })
    public ResponseEntity<ExceptionResponse> handledException(final RuntimeException e) {
        logger.debug(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getMessage()));
    }
}
