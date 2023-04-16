package racingcar.dto;

import java.util.List;

public class GameResultDto {

    private final List<CarDto> racingCars;
    private final String winners;

    public GameResultDto(final List<CarDto> racingCars, final String winners) {
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
