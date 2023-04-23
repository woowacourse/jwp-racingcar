package racingcar.domain;

import static racingcar.option.Option.MIN_TRIAL_COUNT;

public class TrialCount {

    private final int trialCount;

    private TrialCount(int trialCount) {
        this.trialCount = trialCount;
    }

    public static TrialCount of(int trialCount) {
        validateNotNegativeInteger(trialCount);
        return new TrialCount(trialCount);
    }

    private static void validateNotNegativeInteger(int trialCount) {
        if (trialCount <= MIN_TRIAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수를 다시 입력해 주세요.");
        }
    }

    public int getTrialCount() {
        return trialCount;
    }
}
