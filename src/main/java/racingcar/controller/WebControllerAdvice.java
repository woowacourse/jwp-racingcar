package racingcar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.controller.dto.ExceptionResponse;

import java.sql.SQLException;

@ControllerAdvice(assignableTypes = WebController.class)
public class WebControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(WebControllerAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {
        logger.error(exception.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage());
        return ResponseEntity.status(status)
                .body(exceptionResponse);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionResponse> handleSQLException(final SQLException exception) {
        logger.error(exception.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                status.value(),
                status.getReasonPhrase(),
                "해당 요청의 데이터를 처리하는 과정에서 문제가 발생했습니다.");
        return ResponseEntity.status(status)
                .body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleUnexpectedException(final Exception exception) {
        logger.error(exception.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                status.value(),
                status.getReasonPhrase(),
                "예기치 못한 에러가 발생했습니다.");
        return ResponseEntity.status(status)
                .body(exceptionResponse);
    }
}
