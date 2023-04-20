package racingcar.controller.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<List<ExceptionResponse>> requestFormatException(final MethodArgumentNotValidException e) {
        List<ExceptionResponse> exceptions = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(ExceptionResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(exceptions);
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ExceptionResponse> unhandledException(final Exception e) {
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse("예상치 못한 예외가 발생했습니다."));
    }
}
