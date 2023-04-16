package racingcar.exception;

import org.springframework.http.HttpStatus;

public class BlankNameException extends RacingCarException {

    private static final String DEFAULT_MESSAGE = "이름은 공백일 수 없습니다.";

    public BlankNameException() {
        super(DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
