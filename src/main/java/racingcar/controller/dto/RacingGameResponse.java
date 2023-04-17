package racingcar.controller.dto;

import java.util.List;

public class RacingGameResponse {

    private String winners;
    private List<CarDto> racingCars;

    public RacingGameResponse(final String winners, final List<CarDto> racingCars) {
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
