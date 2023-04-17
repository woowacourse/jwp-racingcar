package racingcar.dto;

import java.util.List;

public class HistoryResponseDto {
    private final String winners;
    private final List<RacingCarDto> racingCars;

    public HistoryResponseDto(final String winners, final List<RacingCarDto> racingCars) {
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
