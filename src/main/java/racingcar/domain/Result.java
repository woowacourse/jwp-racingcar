package racingcar.domain;

import java.util.List;

public class Result {
    private final List<Car> winners;
    private final List<Car> cars;

    public Result(final List<Car> winners, final List<Car> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public List<Car> getWinners() {
        return winners;
    }

    public List<Car> getCars() {
        return cars;
    }
}
