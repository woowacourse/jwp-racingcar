package racingcar.exception;

public class ExceedCarNameLengthException extends CustomException {

    public ExceedCarNameLengthException() {
        super("자동차 이름은 다섯 글자 이하여야 합니다.");
    }
}
