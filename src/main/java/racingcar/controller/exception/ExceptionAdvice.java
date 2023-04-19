package racingcar.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ExceptionResponse> unhandledException(final Exception e) {
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse("에상치 못한 예외가 발생했습니다."));
    }
}
