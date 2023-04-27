package racingcar.dto;

import java.util.List;

public class PlayRecordsForResponse {
    private final String winners;
    private final List<CarForSave> racingCars;

    public PlayRecordsForResponse(String winners, List<CarForSave> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarForSave> getRacingCars() {
        return racingCars;
    }
}
