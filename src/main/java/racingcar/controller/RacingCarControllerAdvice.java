package racingcar.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RacingCarControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> loggingUnExpectedException(final Exception e) {
        logger.error("예상치 못한 에러 발생 : {}", convertToString(e));
        return ResponseEntity.internalServerError()
                .body(e.getMessage());
    }

    private String convertToString(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
