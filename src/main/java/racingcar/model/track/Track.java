package racingcar.model.track;

import racingcar.exception.InvalidRangeTrialTimesException;
import racingcar.model.car.Cars;

public class Track {
    private static final int TRIAL_MAX_TIMES = 100;
    private static final int TRIAL_MIN_TIMES = 1;
    private static final int ONE_GAME = 1;
    private static final int CAN_GAME = 0;
    private static final String INVALID_RANGE_ERROR_MESSAGE = "최소 1이상 100이하의 수만 입력할 수 있습니다";

    private final Cars cars;
    private int trialTimes;

    public Track(final Cars cars, final Integer trialTimes) {
        validate(trialTimes);

        this.cars = cars;
        this.trialTimes = trialTimes;
    }

    private void validate(final Integer trialTimes) {
        validateRange(trialTimes);
    }

    private void validateRange(final Integer trialTimes) {

        if (trialTimes < TRIAL_MIN_TIMES || trialTimes > TRIAL_MAX_TIMES) {
            throw new InvalidRangeTrialTimesException(INVALID_RANGE_ERROR_MESSAGE);
        }
    }

    public Cars race() {
        cars.moveCars();
        removeTrialTimes();
        return cars;
    }

    private void removeTrialTimes() {
        trialTimes -= ONE_GAME;
    }

    public boolean runnable() {
        return trialTimes >= CAN_GAME;
    }

    public Cars getCars() {
        return cars;
    }

    public int getTrialTimes() {
        return trialTimes;
    }
}
