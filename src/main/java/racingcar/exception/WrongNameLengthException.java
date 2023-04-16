package racingcar.exception;

import org.springframework.http.HttpStatus;

public class WrongNameLengthException extends RacingCarException {

    private static final String DEFAULT_MESSAGE = "1 ~ 5글자 사이의 이름을 입력해주세요.";

    public WrongNameLengthException() {
        super(DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
