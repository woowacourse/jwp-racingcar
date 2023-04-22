package racingcar.presentation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.presentation.dto.ExceptionResponse;

@RestControllerAdvice
public class DomainExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<ExceptionResponse> handledException(final IllegalArgumentException e) {
        logger.debug(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getMessage()));
    }
}
