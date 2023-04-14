package racingcar.domain;

public class TrialCount {

    private static final int MIN_VALUE = 0;

    private final int trialCount;

    public TrialCount(final int trialCount) {
        validateRange(trialCount);
        this.trialCount = trialCount;
    }

    private void validateRange(final int trialCount) {
        if (trialCount <= MIN_VALUE) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다. 입력 : " + trialCount);
        }
    }

    public int getTrialCount() {
        return trialCount;
    }
}
