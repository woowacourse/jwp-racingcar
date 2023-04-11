package racingcar.dto.response;

import java.util.List;

public class RacingResultResponse {
    private final List<RacingCarResponse> racingCars;
    private final String winners;

    public RacingResultResponse(List<RacingCarResponse> racingCars, String winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<RacingCarResponse> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
