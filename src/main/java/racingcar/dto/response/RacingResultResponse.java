package racingcar.dto.response;

import java.util.List;

public class RacingResultResponse {

    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public RacingResultResponse(String winners, List<RacingCarResponse> racingCars) {
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
