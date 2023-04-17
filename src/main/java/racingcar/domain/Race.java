package racingcar.domain;

import racingcar.common.ErrorData;
import racingcar.common.exception.ResourceMinRangeException;

public class Race {

    private static final int MIN_RACE_COUNT = 0;

    private final int count;

    public Race(final int raceCount) {
        validateRange(raceCount);
        this.count = raceCount;
    }

    public void run(final Cars cars) {
        int tryCount = 0;
        while (isRunning(tryCount++)) {
            cars.race();
        }
    }

    private void validateRange(final int raceCount) {
        if (raceCount <= MIN_RACE_COUNT) {
            throw new ResourceMinRangeException(new ErrorData<>(MIN_RACE_COUNT));
        }
    }

    private boolean isRunning(final int raceCount) {
        return raceCount != count;
    }
}
