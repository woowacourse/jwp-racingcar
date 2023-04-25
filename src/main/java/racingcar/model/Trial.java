package racingcar.model;

public class Trial {
    private static final int MINIMUM_TRY_COUNT = 1;

    public final int trial;

    public Trial(int trial) {
        validateBound(trial);
        this.trial = trial;
    }

    private void validateBound(int trial) {
        if (trial < MINIMUM_TRY_COUNT) {
            throw new IllegalArgumentException("시도 횟수는 " + MINIMUM_TRY_COUNT + "미만일 수 없어요.");
        }
    }

    public int getTrial() {
        return trial;
    }
}
