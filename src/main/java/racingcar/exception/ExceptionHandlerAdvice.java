package racingcar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlNullException(MethodArgumentNotValidException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().body("잘못된 입력입니다.");
    }

}
