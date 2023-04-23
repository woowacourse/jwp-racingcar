package racingcar.model;

import racingcar.exception.CustomException;

import java.util.regex.Pattern;

public class TrialTimes {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d)+");
    private static final int TRIAL_MAX_TIMES = 100;
    private static final int TRIAL_MIN_TIMES = 1;
    private static final int TRY_ONE_GAME = 1;
    private static final int AVAILABLE_GAME = 0;

    private int value;

    private TrialTimes(final String value) {
        validate(value);

        this.value = Integer.parseInt(value);
    }

    public static TrialTimes from(final String value) {


        return new TrialTimes(value);
    }

    private void validate(final String trialTimes) {
        validateNumber(trialTimes);
        validateRange(trialTimes);
    }

    private void validateNumber(final String trialTimes) {
        if (!NUMBER_PATTERN.matcher(trialTimes).matches()) {
            throw new CustomException("시도 횟수는 숫자만 입력 가능합니다.");
        }
    }

    private void validateRange(final String trialTimes) {
        int trialTimesNumber = Integer.parseInt(trialTimes);

        if (trialTimesNumber < TRIAL_MIN_TIMES || trialTimesNumber > TRIAL_MAX_TIMES) {
            throw new CustomException("시도 횟수는 1 이상 100 이하여야 합니다.");
        }
    }

    public void use() {
        if (value < AVAILABLE_GAME) {
            throw new CustomException("최대 시도 횟수를 초과했습니다.");
        }

        value -= TRY_ONE_GAME;
    }

    public boolean runnable() {
        return value >= AVAILABLE_GAME;
    }

    public int getValue() {
        return value;
    }
}
