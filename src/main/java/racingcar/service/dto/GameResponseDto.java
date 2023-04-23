package racingcar.service.dto;

import java.util.List;

public class GameResponseDto {
    private final String winners;
    private final List<PlayerResultResponseDto> racingCars;

    public GameResponseDto(final String winners, final List<PlayerResultResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultResponseDto> getRacingCars() {
        return racingCars;
    }
}
