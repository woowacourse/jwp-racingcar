package racingcar.dto;

import java.util.List;

public class ResultResponse {
    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public ResultResponse(final String winners, final List<RacingCarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponse> getRacingCars() {
        return racingCars;
    }
}
