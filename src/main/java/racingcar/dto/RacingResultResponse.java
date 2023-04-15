package racingcar.dto;

import java.util.List;

public class RacingResultResponse {
    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public RacingResultResponse(List<String> winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
