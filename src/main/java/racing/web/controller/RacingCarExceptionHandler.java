package racing.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racing.common.exception.BusinessException;
import racing.common.exception.ExceptionResponse;

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

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException businessException) {
        return ResponseEntity
                .status(businessException.getHttpStatus())
                .body(new ExceptionResponse(businessException));
    }
}
