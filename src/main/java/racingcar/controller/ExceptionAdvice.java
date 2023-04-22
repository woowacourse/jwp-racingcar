package racingcar.controller;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.domain.exception.RacingCarIllegalArgumentException;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors().stream()
                .filter(error -> Objects.nonNull(error.getDefaultMessage()))
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(), DefaultMessageSourceResolvable::getDefaultMessage)
                );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RacingCarIllegalArgumentException.class)
    public String handle(RacingCarIllegalArgumentException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handle(Exception exception) {
        log.info("[ERROR] {}", exception.getClass());
        log.info("[ERROR] message: {}", exception.getMessage());
        log.info("[ERROR] {}", exception.getStackTrace());
        return "서버 오류가 발생했습니다.";
    }
}
