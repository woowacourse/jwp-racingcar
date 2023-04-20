package racingcar.presentation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.presentation.dto.ExceptionResponse;
import racingcar.domain.exception.CarNameBlankException;
import racingcar.domain.exception.CarNameLengthException;
import racingcar.domain.exception.NoCarsExistException;
import racingcar.domain.exception.PositionInvalidException;

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
