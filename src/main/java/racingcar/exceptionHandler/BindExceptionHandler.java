package racingcar.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BindExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindExceptionHandler(final BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        return ResponseEntity.badRequest().body(fieldError.getDefaultMessage());
    }
}
