package racingcar.exception;

import org.springframework.http.HttpStatus;

public class CommaNotFoundException extends RuntimeExceptionImpl {

    private static final String ENTER_NAME_WITH_COMMA = "쉼표로 이름을 구분해주세요.";
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public CommaNotFoundException() {
        super(ENTER_NAME_WITH_COMMA, httpStatus);
    }
}
