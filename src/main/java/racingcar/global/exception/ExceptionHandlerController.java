package racingcar.global.exception;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.global.exception.response.DefaultExceptionResponse;
import racingcar.global.exception.response.ExceptionResponse;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        return ExceptionResponse.of(ExceptionStatus.INVALID_INPUT_VALUE_EXCEPTION, exception);
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<DefaultExceptionResponse> handleDefaultException(
            Exception exception
    ) {
        return ResponseEntity.badRequest()
                             .body(DefaultExceptionResponse.from(exception));
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleDataAccessException() {
        return ExceptionResponse.of(ExceptionStatus.TEMPORARY_DATABASE_CONNECTION_EXCEPTION);
    }
}
