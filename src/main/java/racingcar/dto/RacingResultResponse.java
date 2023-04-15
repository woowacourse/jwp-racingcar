package racingcar.dto;

import java.util.List;

public class RacingResultResponse {
    private final String winners;
    private final List<RacingCarDto> racingCars;

    public RacingResultResponse(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
