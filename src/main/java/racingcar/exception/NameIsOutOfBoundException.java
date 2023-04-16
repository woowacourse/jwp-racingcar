package racingcar.exception;

import org.springframework.http.HttpStatus;

public class NameIsOutOfBoundException extends RuntimeExceptionImpl {

    private static final String ENTER_NAME_CORRECT_LENGTH = "이름은 5글자 이하로 입력해주세요.";
    private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public NameIsOutOfBoundException() {
        super(ENTER_NAME_CORRECT_LENGTH, httpStatus);
    }
}
