package racingcar.domain;

public class Race {

    private static final int RACE_MIN_TRY_COUNT = 0;
    private static final String RANGE_MESSAGE = "%d 이상의 값을 입력해 주세요.";

    private final int count;

    public Race(final int raceCount) {
        this.count = validateRange(raceCount);
    }

    public Cars run(final Cars cars) {
        int tryCount = 0;
        Cars currentCars = cars;
        while (isRunning(tryCount++)) {
            currentCars = currentCars.race();
        }
        return currentCars;
    }

    private boolean isRunning(final int raceCount) {
        return raceCount != count;
    }

    private int validateRange(final int raceCount) {
        if (raceCount <= RACE_MIN_TRY_COUNT) {
            throw new IllegalArgumentException(String.format(RANGE_MESSAGE, RACE_MIN_TRY_COUNT));
        }
        return raceCount;
    }
}
