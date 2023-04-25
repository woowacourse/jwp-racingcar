package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.controller.dto.ExceptionDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> inputExceptionHandler(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest()
                .body(new ExceptionDto(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> serverExceptionHandler(Exception exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ExceptionDto("서버에 문제가 발생했습니다."));
    }

}
