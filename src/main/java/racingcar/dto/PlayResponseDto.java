package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public PlayResponseDto(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
