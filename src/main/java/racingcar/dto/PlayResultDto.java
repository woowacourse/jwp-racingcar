package racingcar.dto;

import java.util.List;

public class PlayResultDto {

    private final List<CarDto> racingCars;
    private final String winners;

    public PlayResultDto(final List<CarDto> racingCars, final String winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
