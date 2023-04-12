package racingcar.dto;

import java.util.List;

public class GameResponse {
    private String winners;
    private List<CarResponse> racingCars;

    public GameResponse(final String winners, final List<CarResponse> racingCars) {
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
