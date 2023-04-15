package racingcar.dto;

import java.util.List;

public class RacingGameResponseDto {

    private final List<String> winners;
    private final List<CarStatusDto> racingCars;

    public RacingGameResponseDto(final List<String> winners, final List<CarStatusDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarStatusDto> getRacingCars() {
        return racingCars;
    }
}
