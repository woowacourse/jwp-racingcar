package racingcar.dto;

import java.util.List;

public class ResultDto {
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public ResultDto(List<String> winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public boolean isWinnerContaining(String name) {
        return winners.contains(name);
    }
}
