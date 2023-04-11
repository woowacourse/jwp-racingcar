package racingcar.dto;

import java.util.List;

public class ResultResponse {
    private final String winners;
    private final List<RacingCarsResponse> racingCars;

    public ResultResponse(final String winners, final List<RacingCarsResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarsResponse> getRacingCars() {
        return racingCars;
    }
}
