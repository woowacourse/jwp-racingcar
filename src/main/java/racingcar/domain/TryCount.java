package racingcar.domain;

public class TryCount {
    private static final int MIN_TRY_COUNT = 0;

    private final int value;

    public TryCount(int value) {
        validateCountInput(value);
        this.value = value;
    }

    private void validateCountInput(int tryCount) {
        if (tryCount <= MIN_TRY_COUNT) {
            throw new IllegalArgumentException("시도할 횟수는 0보다 큰 숫자여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
