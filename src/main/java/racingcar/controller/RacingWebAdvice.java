package racingcar.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.CustomException;
import racingcar.exception.ExceptionInfo;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RacingWebAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionInfo> customHandler(final CustomException customException) {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(customException.getErrorMessage());
        return exceptionInfo.makeErrorResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> handleValidationExceptions(
            final MethodArgumentNotValidException exception) {
        final String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        final ExceptionInfo exceptionInfo = new ExceptionInfo(errorMessage);

        return exceptionInfo.makeErrorResponse();
    }
}
