package racingcar.exception;

public class InvalidRangeTrialTimesException extends CustomException {

    public InvalidRangeTrialTimesException() {
        super("시도 횟수는 1 이상 100 이하여야 합니다.");
    }
}
