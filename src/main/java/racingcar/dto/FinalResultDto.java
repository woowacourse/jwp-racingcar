package racingcar.dto;

import java.util.List;

public class FinalResultDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public FinalResultDto(ResultDto resultDto) {
        this.winners = resultDto.getWinners();
        this.racingCars = resultDto.getRacingCars();
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
