package racingcar.domain;

public class TrialCount {

    private static final int MIN_VALUE = 0;

    private final int value;

    public TrialCount(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value <= MIN_VALUE) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다. 입력 : " + value);
        }
    }

    public int getValue() {
        return value;
    }
}
