package racingcar.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResult {
    private final String code;
    private final String message;

    private ErrorResult(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ErrorResult> toResponseEntity(final String code, final String message) {
        final ErrorResult errorResult = new ErrorResult(String.valueOf(code), message);
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
