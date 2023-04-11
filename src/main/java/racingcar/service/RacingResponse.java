package racingcar.service;

import java.util.List;

public class RacingResponse {

    private final String winners;
    private final List<PlayerResult> racingCars;

    public RacingResponse(final String winners, final List<PlayerResult> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResult> getRacingCars() {
        return racingCars;
    }
}
