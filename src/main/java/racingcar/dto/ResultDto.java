package racingcar.dto;

import java.util.List;

public class ResultDto {

    private final List<String> winnerNames;
    private final List<CarDto> racingCars;

    public ResultDto(List<String> winnerNames, List<CarDto> racingCars) {
        this.winnerNames = winnerNames;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winnerNames;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
