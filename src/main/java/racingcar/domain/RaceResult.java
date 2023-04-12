package racingcar.domain;

import java.util.List;

public class RaceResult {

    private final int trialCount;
    private final String winners;
    private final Cars cars;

    public RaceResult(int trialCount, String winners, Cars cars) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.cars = cars;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getCars() {
        return List.copyOf(cars.getCars());
    }
}
