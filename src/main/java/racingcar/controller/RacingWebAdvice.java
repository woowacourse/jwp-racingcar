package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.CustomException;
import racingcar.exception.ExceptionInfo;

@RestControllerAdvice
public class RacingWebAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionInfo> customHandler(final CustomException customException) {
        final ExceptionInfo exceptionInfo = new ExceptionInfo(customException.getErrorMessage());
        return exceptionInfo.makeErrorResponse();
    }
}
