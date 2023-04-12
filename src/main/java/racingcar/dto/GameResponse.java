package racingcar.dto;

import java.util.List;

public class GameResponse {
    private final String winners;
    private final List<CarDto> racingCars;

    public GameResponse(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
