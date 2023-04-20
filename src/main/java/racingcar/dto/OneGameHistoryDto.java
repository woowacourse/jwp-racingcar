package racingcar.dto;

import java.util.List;

public class OneGameHistoryDto {

    private final String winners;
    private final List<RacingCarDto> racingCars;

    public OneGameHistoryDto(final String winners, final List<RacingCarDto> racingCars) {
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
