package racingcar.global.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public enum ExceptionStatus {

    INVALID_INPUT_VALUE_EXCEPTION(400, "유효하지 않는 입력 값입니다.", BAD_REQUEST),
    TEMPORARY_DATABASE_CONNECTION_EXCEPTION(512, "일시적인 데이터베이스 오류입니다. 재시도 해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);


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
