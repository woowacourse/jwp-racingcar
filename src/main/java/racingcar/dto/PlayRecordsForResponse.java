package racingcar.dto;

import java.util.List;

public class PlayRecordsForResponse {
    private final String winners;
    private final List<CarForNameAndPosition> racingCars;

    public PlayRecordsForResponse(String winners, List<CarForNameAndPosition> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarForNameAndPosition> getRacingCars() {
        return racingCars;
    }
}
