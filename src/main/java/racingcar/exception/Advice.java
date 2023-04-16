package racingcar.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionMessageDTO;

@RestControllerAdvice
public class Advice {

    public static final int ILLEGAL_INPUT_ERROR = 20;
    public static final int UNKNOWN_ERROR = -1;

    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionMessageDTO handleIllegalInput(IllegalArgumentException exception) {
        return new ExceptionMessageDTO(ILLEGAL_INPUT_ERROR, exception.getMessage());
    }

    @ExceptionHandler
    public ExceptionMessageDTO handleException(Exception exception) {
        return new ExceptionMessageDTO(UNKNOWN_ERROR, exception.getMessage());
    }

}
