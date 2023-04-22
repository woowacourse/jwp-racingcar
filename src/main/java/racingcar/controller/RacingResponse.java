package racingcar.controller;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;

public class RacingResponse {

    private final String winners;
    private final List<Car> racingCars;

    public RacingResponse(final List<Car> winnerCars, final List<Car> racingCars) {
        this.winners = makeWinners(winnerCars);
        this.racingCars = racingCars;
    }

    private String makeWinners(final List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
