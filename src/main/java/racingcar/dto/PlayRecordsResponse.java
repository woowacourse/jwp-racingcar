package racingcar.dto;

import java.util.List;

public class PlayRecordsResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    public PlayRecordsResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
