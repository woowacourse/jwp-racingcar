package racingcar.dto;

import java.util.List;

public class PlayRecordsResponse {
    private final String winners;
    private final List<CarParam> racingCars;

    public PlayRecordsResponse(String winners, List<CarParam> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarParam> getRacingCars() {
        return racingCars;
    }

}
