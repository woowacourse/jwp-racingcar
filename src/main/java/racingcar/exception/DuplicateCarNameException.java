package racingcar.exception;

import org.springframework.http.HttpStatus;

public class DuplicateCarNameException extends RacingCarException {

    private static final String DEFAULT_MESSAGE = "이름은 중복될 수 없습니다.";

    public DuplicateCarNameException() {
        super(DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
