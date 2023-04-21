package racingcar.controller;

import java.util.List;

public class TrackCreateResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    public TrackCreateResponse(final String winners, final List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
