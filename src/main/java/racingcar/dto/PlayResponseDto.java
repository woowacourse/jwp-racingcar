package racingcar.dto;

import java.util.List;

public class PlayResponseDto {
    private final String winners;
    private final List<RacingCarDto> racingCars;

    public PlayResponseDto(List<String> winners, List<RacingCarDto> racingCars) {
        this.winners = String.join(",", winners);
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
