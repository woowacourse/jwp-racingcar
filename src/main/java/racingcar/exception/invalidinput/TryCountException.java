package racingcar.exception.invalidinput;

public class TryCountException extends InvalidInputException {
    private static final String TRY_COUNT_EXCEPTION_MESSAGE = "[ERROR] 0보다 큰 숫자를 입력해야 합니다.";

    public TryCountException() {
        super(TRY_COUNT_EXCEPTION_MESSAGE);
    }
}
