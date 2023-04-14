package racingcar.common;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.common.exception.DuplicateResourceException;
import racingcar.common.exception.ResourceLengthException;
import racingcar.common.exception.ResourceMinRangeException;
import racingcar.dto.ExceptionResponse;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RaceControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException() {
        final ExceptionResponse exceptionResponse = new ExceptionResponse("입력 형식이 맞지 않습니다.");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(
            final MethodArgumentNotValidException e) {
        final String errorMessage = getErrorMessage(e);
        return ResponseEntity.badRequest().body(new ExceptionResponse(errorMessage));
    }

    @ExceptionHandler(ResourceLengthException.class)
    public ResponseEntity<ExceptionResponse> resourceLengthException(final ResourceLengthException e) {
        final Integer lengthLimit = e.getLength().getData();
        final ExceptionResponse exceptionResponse =
                new ExceptionResponse(String.format("최대 %d글자까지 입력할 수 있습니다.", lengthLimit));
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponse> duplicateResourceException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse("중복된 값을 입력할 수 없습니다."));
    }

    @ExceptionHandler(ResourceMinRangeException.class)
    public ResponseEntity<ExceptionResponse> resourceRangeException(final ResourceMinRangeException e) {
        final Integer minRange = e.getRange().getData();
        final ExceptionResponse exceptionResponse =
                new ExceptionResponse(String.format("%d보다 큰 값을 입력해 주세요.", minRange));
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(final Exception e) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse("오류가 발생하였습니다, 오류 = " + e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    private String getErrorMessage(final MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
    }
}
