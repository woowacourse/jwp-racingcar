package racingcar.service.dto;

import java.util.List;

public class RaceResultResponse {

    private final String winners;
    private final List<CarStatusResponse> racingCars;

    public RaceResultResponse(final String winners, final List<CarStatusResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStatusResponse> getRacingCars() {
        return racingCars;
    }
}
