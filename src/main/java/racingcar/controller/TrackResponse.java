package racingcar.controller;

import java.util.List;

public class TrackResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    public TrackResponse(final String winners, final List<CarResponse> racingCars) {
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
