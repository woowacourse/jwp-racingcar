package racingcar.domain;

public class TrialCount {

    private static final int MIN_TRIAL_COUNT = 0;
    private static final int DECREASE_AMOUNT = 1;

    private final int initial;
    private final int left;

    public TrialCount(int left) {
        this(left, left);
    }

    private TrialCount(int initial, int left) {
        validateNotNegative(initial);
        validateNotNegative(left);
        this.initial = initial;
        this.left = left;
    }

    private void validateNotNegative(int count) {
        if (count < MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("시도횟수는 음수이면 안됩니다");
        }
    }

    public TrialCount decrease() {
        return new TrialCount(initial, left - DECREASE_AMOUNT);
    }

    public boolean isLeft() {
        return left > MIN_TRIAL_COUNT;
    }

    public int getDecreasedCount() {
        return initial - left;
    }
}
