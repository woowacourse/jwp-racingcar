package racingcar.exception;

import org.springframework.http.HttpStatus;

public class DatabaseAccessException extends RuntimeExceptionImpl {

    private static final String CANNOT_ACCESS_DATABASE_MESSAGE = "데이터베이스에 접근할 수 없습니다.";
    private static final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;


    public DatabaseAccessException() {
        super(CANNOT_ACCESS_DATABASE_MESSAGE, httpStatus);
    }
}
