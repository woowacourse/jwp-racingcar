package racingcar.dto;

import java.util.List;

public class RacingGameResultDto {
    private final String winners;

    private final List<PlayerResultDto> racingCars;

    public RacingGameResultDto(final String winners, final List<PlayerResultDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultDto> getRacingCars() {
        return racingCars;
    }
}
