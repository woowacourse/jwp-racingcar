package racingcar.dto.response;

import java.util.List;

public class RacingGameResultResponse {

    private final List<CarResponse> racingCars;
    private final String winners;

    public RacingGameResultResponse(List<CarResponse> racingCars, String winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
