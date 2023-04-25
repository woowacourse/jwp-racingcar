package racingcar.exception;

public class InvalidTrialTimesFormatException extends CustomException {

    public InvalidTrialTimesFormatException() {
        super("시도 횟수는 숫자만 입력 가능합니다.");
    }
}
