package racingcar.exception;

public class PositionInvalidException extends RuntimeException {

    private final static String MESSAGE = "[ERROR] 자동차의 위치 값은 음수일 수 없습니다.";

    public PositionInvalidException() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
