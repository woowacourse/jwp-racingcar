package racingcar.dto;

import java.util.List;

public final class GameResultResponse {

    private final String winners;
    private final List<CarData> racingCars;

    public GameResultResponse(final String winners, final List<CarData> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarData> getRacingCars() {
        return racingCars;
    }
}
