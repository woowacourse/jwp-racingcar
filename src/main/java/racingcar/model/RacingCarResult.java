package racingcar.model;

import java.util.List;

public class RacingCarResult {
    private final List<String> winners;
    private final List<Car> racingCars;

    public RacingCarResult(final List<String> winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public boolean isWinner(final Car car) {
        return winners.contains(car.getName());
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
