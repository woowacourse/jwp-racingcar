package racingcar.exception;

import org.springframework.http.HttpStatus;

public class CannotCreateRandomException extends RuntimeExceptionImpl {

    private static final String CANNOT_CREATE_RANDOM = "랜덤 객체를 생성할 수 없습니다.";
    private static final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public CannotCreateRandomException() {
        super(CANNOT_CREATE_RANDOM, httpStatus);
    }
}

