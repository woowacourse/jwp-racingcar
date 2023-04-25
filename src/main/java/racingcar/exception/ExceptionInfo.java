package racingcar.exception;

import org.springframework.http.ResponseEntity;

public class ExceptionInfo {

    private final String message;

    public ExceptionInfo(final String message) {
        this.message = message;
    }

    public ResponseEntity<ExceptionInfo> makeErrorResponse() {
        return ResponseEntity.badRequest().body(this);
    }

    public String getMessage() {
        return message;
    }
}
