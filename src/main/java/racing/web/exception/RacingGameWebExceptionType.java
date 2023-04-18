package racing.web.exception;

import org.springframework.http.HttpStatus;

public enum RacingGameWebExceptionType {
    CAR_NAME_DUPLICATION(HttpStatus.BAD_REQUEST, "동일한 자동차 이름은 입력될 수 없습니다."),
    CAR_NAME_BLANK(HttpStatus.BAD_REQUEST, "자동차 이름이 비어있습니다."),
    CAR_NAME_INVALID_RANGE(HttpStatus.BAD_REQUEST, "자동차 이름은 1 ~ 5자 사이 이어야 합니다."),
    UNKNOWN_INTERNAL_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 오류가 발생하였습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String exceptionMessage;

    RacingGameWebExceptionType(HttpStatus httpStatus, String exceptionMessage) {
        this.httpStatus = httpStatus;
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
