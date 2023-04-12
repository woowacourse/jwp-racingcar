package racingcar.dto;

import java.util.List;

public class FinalResultDto {
    private final boolean success = true;
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public FinalResultDto(List<String> winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
