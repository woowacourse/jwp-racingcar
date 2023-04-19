package racingcar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import racingcar.exception.invalidinput.InvalidInputException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleBlankException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body("잘못된 입력입니다.");
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInput(InvalidInputException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
