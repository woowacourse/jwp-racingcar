package racingcar.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RacingCarControllerAdvice {

    private static final String UNEXPECTED_ERROR_LOG_FORMAT = "예상치 못한 에러 발생 : " + System.lineSeparator() + "{}";
    //좀 더 적절한 메시지가 있을지 고민해보기.
    private static final String UNEXPECTED_ERROR_MESSAGE = "네트워크 지연으로 다시 시도해주시기 바랍니다.";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> loggingUnExpectedException(final Exception e) {
        logger.error(UNEXPECTED_ERROR_LOG_FORMAT, convertToString(e));
        return ResponseEntity.internalServerError()
                .body(UNEXPECTED_ERROR_MESSAGE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private String convertToString(final Exception e) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
