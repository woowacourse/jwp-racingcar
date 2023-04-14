package racingcar.dto;

import java.util.List;

public class GameResponseDto {
    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public GameResponseDto(List<String> winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }
}
