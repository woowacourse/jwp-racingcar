package racingcar.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;

@RestControllerAdvice
public class Advice {

    public static final int ILLEGAL_INPUT_ERROR = 20;
    public static final int UNKNOWN_ERROR = -1;

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionResponse handleIllegalInput(IllegalArgumentException exception) {
        return new ExceptionResponse(ILLEGAL_INPUT_ERROR, exception.getMessage());
    }

    @ExceptionHandler
    public ExceptionResponse handleException(Exception exception) {
        return new ExceptionResponse(UNKNOWN_ERROR, exception.getMessage());
    }

}
