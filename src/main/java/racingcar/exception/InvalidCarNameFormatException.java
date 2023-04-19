package racingcar.exception;

public class InvalidCarNameFormatException extends CustomException {

    public InvalidCarNameFormatException() {
        super("자동차 이름은 문자와 숫자만 가능합니다.");
    }
}
