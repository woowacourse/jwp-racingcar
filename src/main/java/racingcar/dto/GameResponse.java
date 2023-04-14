package racingcar.dto;

import java.util.List;

public class GameResponse {
    private final String winners;
    private final List<CarDto> racingCars;

    private GameResponse(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse of(final String winners, final List<CarDto> racingCars) {
        return new GameResponse(winners, racingCars);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
