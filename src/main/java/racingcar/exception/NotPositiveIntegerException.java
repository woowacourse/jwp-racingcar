package racingcar.exception;

import org.springframework.http.HttpStatus;

public class NotPositiveIntegerException extends RuntimeExceptionImpl {

    private static final String ENTER_POSITIVE_INTEGER = "양의 정수를 입력해주세요.";
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public NotPositiveIntegerException() {
        super(ENTER_POSITIVE_INTEGER, httpStatus);
    }
}
