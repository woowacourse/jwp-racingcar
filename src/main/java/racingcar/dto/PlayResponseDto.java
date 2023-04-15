package racingcar.dto;

import java.util.List;

public class PlayResponseDto {

    private final List<CarDto> racingCars;
    private final String winners;

    public PlayResponseDto(final List<CarDto> racingCars, final String winners) {
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
