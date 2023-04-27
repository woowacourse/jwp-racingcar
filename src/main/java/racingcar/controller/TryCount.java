package racingcar.controller;

public class TryCount {

    private static final int MINIMUM_TRY_COUNT = 5;

    private int value;

    public TryCount(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MINIMUM_TRY_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 5 이상 입력해주세요.");
        }
    }

    public void decrease() {
        value--;
    }

    public boolean isRunnable() {
        return value > 0;
    }
}
