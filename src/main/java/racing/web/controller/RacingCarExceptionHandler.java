package racing.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racing.web.exception.ExceptionResponse;
import racing.web.exception.RacingGameWebException;

@RestControllerAdvice
public class RacingCarExceptionHandler {

    private static final String UNKNOWN_INTERNAL_SERVER_EXCEPTION_MESSAGE = "알 수 없는 서버 오류가 발생하였습니다.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleWideScopeException() {
        ExceptionResponse exception = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                UNKNOWN_INTERNAL_SERVER_EXCEPTION_MESSAGE);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception);
    }

    @ExceptionHandler(RacingGameWebException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(RacingGameWebException racingGameWebException) {
        return ResponseEntity
                .status(racingGameWebException.getHttpStatus())
                .body(new ExceptionResponse(racingGameWebException));
    }
}
