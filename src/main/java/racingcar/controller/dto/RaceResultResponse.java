package racingcar.controller.dto;

import java.util.List;

public class RaceResultResponse {

    private final List<String> winners;
    private final List<CarStatusResponse> racingCars;

    public RaceResultResponse(final List<String> winners, final List<CarStatusResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarStatusResponse> getRacingCars() {
        return racingCars;
    }
}
