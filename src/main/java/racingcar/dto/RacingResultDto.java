package racingcar.dto;

import java.util.List;

public class RacingResultDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public RacingResultDto(List<String> winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }
}
