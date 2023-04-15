package racingcar.dto;

import java.util.List;

public class RacingResultResponseDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public RacingResultResponseDto(RacingResultDto racingResultDto) {
        this.winners = racingResultDto.getWinners();
        this.racingCars = racingResultDto.getRacingCars();
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
