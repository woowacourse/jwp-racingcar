package racingcar.domain;

import java.util.List;

public class RacingGameResultDto {
    String winners;
    List<RacingCarDto> racingCars;

    public RacingGameResultDto(final String winners, final List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
