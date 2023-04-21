package racingcar.exception.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.RuntimeExceptionImpl;

@RestControllerAdvice
public class RacingGameExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(final RuntimeExceptionImpl exception) {
        printLog(exception);

        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handle(final DataAccessException exception) {
        printLog(exception);

        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse(exception.getMessage()));
    }

    private void printLog(final Exception exception) {
        log.warn("[예외 발생]  = {}", createExceptionStackTrace(exception));
    }

    private String createExceptionStackTrace(final Exception exception) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
