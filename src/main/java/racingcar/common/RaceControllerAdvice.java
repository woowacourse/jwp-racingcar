package racingcar.common;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.dto.ExceptionDto;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RaceControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> handleHttpMessageNotReadableException() {
        final ExceptionDto exceptionDto = new ExceptionDto("입력 형식이 맞지 않습니다.");
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidExceptionHandler(
            final MethodArgumentNotValidException e) {
        final String errorMessage = getErrorMessage(e);
        return ResponseEntity.badRequest().body(new ExceptionDto(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception() {
        final ExceptionDto exceptionDto = new ExceptionDto("오류가 발생하였습니다");
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    private String getErrorMessage(final MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
    }
}
