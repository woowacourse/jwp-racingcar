package racingcar.domain;

public class Race {

    private final int count;

    public Race(final int raceCount) {
        this.count = raceCount;
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
}
