package racingcar.controller;

import racingcar.model.Car;

import java.util.List;

public class RacingGameLog {

    private final String winners;
    private final List<Car> racingCars;

    public RacingGameLog(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
