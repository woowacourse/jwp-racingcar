package racingcar.dto;

import java.util.List;

public final class GameResultDto {

    private final List<CarDto> winners;
    private final List<CarDto> racingCars;

    public GameResultDto(final List<CarDto> winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<CarDto> getWinners() {
        return this.winners;
    }

    public List<CarDto> getRacingCars() {
        return this.racingCars;
    }
}
