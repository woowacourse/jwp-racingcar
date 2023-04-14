package racingcar.common;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.common.exception.DuplicateResourceException;
import racingcar.common.exception.ResourceLengthException;
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

    @ExceptionHandler(ResourceLengthException.class)
    public ResponseEntity<ExceptionDto> resourceLengthException(final ResourceLengthException e) {
        final Integer nameLimit = e.getLength().getData();
        final ExceptionDto exceptionDto = new ExceptionDto(String.format("최대 %d글자까지 입력할 수 있습니다.", nameLimit));
        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionDto> duplicateResourceException() {
        return ResponseEntity.badRequest().body(new ExceptionDto("중복된 값을 입력할 수 없습니다."));
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
