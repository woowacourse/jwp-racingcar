package racingcar.exception;

import org.springframework.http.HttpStatus;

public class CannotFindMaxPositionException extends RuntimeExceptionImpl {

    private static final String CANNOT_FIND_MAX_POSITION = "최대 거리를 찾을 수 없습니다.";
    private static final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public CannotFindMaxPositionException() {
        super(CANNOT_FIND_MAX_POSITION, httpStatus);
    }
}
