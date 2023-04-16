package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerCarDto {

    private final List<CarDto> winners;
    private final List<CarDto> racingCars;

    public WinnerCarDto(final List<CarDto> winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String joinWinnerNames() {
        return winners.stream().map(CarDto::getName).collect(Collectors.joining(","));
    }

    public List<CarDto> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
