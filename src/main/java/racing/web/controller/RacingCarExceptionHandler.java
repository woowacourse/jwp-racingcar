package racing.web.controller;

import java.util.NoSuchElementException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RacingCarExceptionHandler {

    private static final String UNKNOWN_INTERNAL_SERVER_EXCEPTION_MESSAGE = "알 수 없는 서버 오류가 발생하였습니다.";
    private static final String NO_SUCH_ELEMENT_EXCEPTION = "요청하신 자원이 존재하지 않습니다.";
    private static final String INVALID_NUMBER_REQUEST_EXCEPTION = "숫자 형식에 맞지 않는 요청입니다.";
    private static final String INVALID_FROM_DATA = "형식에 맞지 않는 요청입니다.";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleWideScopeException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(UNKNOWN_INTERNAL_SERVER_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBusinessException(IllegalArgumentException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleInvalidNumberPathRequestException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(INVALID_NUMBER_REQUEST_EXCEPTION);
    }

    @ExceptionHandler({NoSuchElementException.class, DataIntegrityViolationException.class})
    public ResponseEntity<String> handleNotDataExistException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(NO_SUCH_ELEMENT_EXCEPTION);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(INVALID_FROM_DATA);
    }
}
