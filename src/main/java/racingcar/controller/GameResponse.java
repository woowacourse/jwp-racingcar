package racingcar.controller;

import java.util.List;

public class GameResponse {

    private final String winners;
    private final List<RacingCarDto> racingCars;

    public GameResponse(final String winners, final List<RacingCarDto> racingCars) {
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
