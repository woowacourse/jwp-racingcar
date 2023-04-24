package racingcar.controller;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.domain.exception.RacingCarIllegalArgumentException;
import racingcar.dto.ExceptionResponseDto;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponseDto handle(MethodArgumentNotValidException exception) {
        FieldError foundError = (FieldError) exception.getBindingResult()
                .getAllErrors().stream()
                .filter(error -> Objects.nonNull(error.getDefaultMessage()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("예외 메시지 정보가 존재하지 않습니다."));
        return new ExceptionResponseDto(foundError.getField(), foundError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RacingCarIllegalArgumentException.class)
    public ExceptionResponseDto handle(RacingCarIllegalArgumentException exception) {
        return new ExceptionResponseDto(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponseDto handle(Exception exception) {
        log.info("[ERROR] {}", exception.getClass());
        log.info("[ERROR] message: {}", exception.getMessage());
        log.info("[ERROR] {}", exception.getStackTrace());
        return new ExceptionResponseDto("서버 오류가 발생했습니다.");
    }
}
