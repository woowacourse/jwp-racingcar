package racingcar.exception;

public class DuplicateCarNamesException extends CustomException {

    public DuplicateCarNamesException() {
        super("중복된 차 이름이 존재합니다.");
    }
}
