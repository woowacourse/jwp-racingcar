package racingcar.dto;

import java.util.List;

public class RacingResultResponse {

    private final boolean success = true;
    private final List<String> winners;
    private final List<CarResponse> racingCars;

    public RacingResultResponse(List<String> winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
