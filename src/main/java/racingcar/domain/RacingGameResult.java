package racingcar.domain;

import java.util.List;

public class RacingGameResult {

    private final List<Car> totalCars;
    private final List<Car> winners;
    private final Count count;

    public RacingGameResult(final List<Car> totalCars, final List<Car> winners, final Count count) {
        this.totalCars = totalCars;
        this.winners = winners;
        this.count = count;
    }

    public List<Car> getTotalCars() {
        return totalCars;
    }

    public List<Car> getWinners() {
        return winners;
    }

    public Count getCount() {
        return count;
    }
}
