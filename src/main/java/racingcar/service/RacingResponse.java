package racingcar.service;

import java.util.List;

public class RacingResponse {
    private final String winners;
    private final List<CarEntity> racingCars;

    public RacingResponse(final String winners, final List<CarEntity> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarEntity> getRacingCars() {
        return racingCars;
    }
}
