package racingcar.exception;

public class HasBlankCarNameException extends CustomException {

    public HasBlankCarNameException() {
        super("비어있는 자동차 이름이 존재합니다.");
    }
}
