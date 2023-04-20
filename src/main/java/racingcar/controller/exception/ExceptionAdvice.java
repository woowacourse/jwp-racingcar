package racingcar.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<List<ExceptionResponse>> requestFormatException(final MethodArgumentNotValidException e) {
        List<ExceptionResponse> exceptions = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .peek(logger::debug)
                .map(ExceptionResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(exceptions);
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ExceptionResponse> unhandledException(final Exception e) {
        logger.debug(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse("예상치 못한 예외가 발생했습니다."));
    }
}
