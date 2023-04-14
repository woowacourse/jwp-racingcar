package racingcar.domain;

public class Count {

    private static final String LESS_THAN_ZERO_MESSAGE = "횟수가 0보다 작으면 안됩니다";

    private final int targetCount;
    private int now = 0;

    public Count(final int targetCount) {
        validate(targetCount);
        this.targetCount = targetCount;
    }

    private void validate(final int targetCount) {
        if (targetCount < 0) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_MESSAGE);
        }
    }

    public boolean isFinished() {
        return now >= targetCount;
    }

    public void next() {
        now++;
    }

    public int getTargetCount() {
        return targetCount;
    }
}
