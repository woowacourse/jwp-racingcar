package racingcar.model;

import java.util.List;

public class PlayResponse {
    private final String winners;
    private final List<Car> racingCars;

    public PlayResponse(final String winners, final List<Car> racingCars) {
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
