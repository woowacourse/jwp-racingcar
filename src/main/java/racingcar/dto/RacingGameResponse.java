package racingcar.dto;

import java.util.List;

public final class RacingGameResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    public RacingGameResponse(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return this.winners;
    }

    public List<CarDto> getRacingCars() {
        return this.racingCars;
    }
}
