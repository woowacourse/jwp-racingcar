package racingcar.domain;

import racingcar.exception.IllegalGameArgumentException;

public class TrialCount {

    private static final int MIN_TRIAL_COUNT = 0;
    private static final int DECREASE_AMOUNT = 1;

    private final int count;

    public TrialCount(int count) {
        validateNotNegative(count);
        this.count = count;
    }

    private void validateNotNegative(int count) {
        if (count < MIN_TRIAL_COUNT) {
            throw new IllegalGameArgumentException("시도횟수는 음수이면 안됩니다");
        }
    }

    public TrialCount decrease() {
        return new TrialCount(count - DECREASE_AMOUNT);
    }

    public boolean isLeft() {
        return count > MIN_TRIAL_COUNT;
    }

    public int getCount() {
        return count;
    }
}
