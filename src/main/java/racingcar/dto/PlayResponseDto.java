package racingcar.dto;

import java.util.List;

// TODO DTO 네이밍 고민
public class PlayResponseDto {

    private final List<CarResponseDto> racingCars;
    private final String winners;

    public PlayResponseDto(final List<CarResponseDto> racingCars, final String winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<CarResponseDto> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
