package racingcar.model.track;

import racingcar.exception.CustomException;
import racingcar.model.car.Cars;

import java.util.regex.Pattern;

public class Track {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d)+");
    private static final int TRIAL_MAX_TIMES = 100;
    private static final int TRIAL_MIN_TIMES = 1;

    private final Cars cars;
    private int trialTimes;

    private Track(final Cars cars, final String trialTimes) {
        validate(trialTimes);

        this.cars = cars;
        this.trialTimes = Integer.parseInt(trialTimes);
    }

    public static Track of(final Cars cars, final String trialTimes) {
        return new Track(cars, trialTimes);
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

    public Cars race() {
        cars.moveCars();
        removeTrialTimes();
        return cars;
    }

    private void removeTrialTimes() {
        trialTimes -= 1;
    }

    public boolean runnable() {
        return trialTimes >= 0;
    }

    public Cars getCars() {
        return cars;
    }

    public int getTrialTimes() {
        return trialTimes;
    }
}
