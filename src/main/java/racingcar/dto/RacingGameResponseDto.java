package racingcar.dto;

import java.util.List;

public class RacingGameResponseDto {

    private final List<String> winners;
    private final List<CarDto> racingCars;

    public RacingGameResponseDto(final List<String> winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
