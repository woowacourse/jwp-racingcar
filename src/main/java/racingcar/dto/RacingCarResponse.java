package racingcar.dto;

import java.util.List;

public class RacingCarResponse {

    private final String winners;
    private final List<RacingCarData> racingCars;

    public RacingCarResponse(String winners, List<RacingCarData> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarData> getRacingCars() {
        return racingCars;
    }
}
