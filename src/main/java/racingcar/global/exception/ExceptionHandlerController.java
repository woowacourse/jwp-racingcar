package racingcar.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
}
