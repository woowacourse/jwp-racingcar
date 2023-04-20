package racingcar.global.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum ExceptionStatus {

    INVALID_INPUT_VALUE_EXCEPTION(400, "유효하지 않는 입력 값입니다.", BAD_REQUEST);

    private final int status;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionStatus(final int status, final String message, final HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
