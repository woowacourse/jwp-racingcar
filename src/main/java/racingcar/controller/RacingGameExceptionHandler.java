package racingcar.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
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

    @ExceptionHandler(value = {RuntimeExceptionImpl.class, DataAccessException.class})
    public ResponseEntity<Map<String, String>> handle(Exception exception) {
        printLog(exception);

        final Map<String, String> exceptionResponse = new HashMap<>();
        exceptionResponse.put("exception", exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
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
