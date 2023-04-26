package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public final class GameResultDto {

    private final String winners;
    private final List<CarDto> racingCars;

    public GameResultDto(final List<CarDto> cars, final List<WinnerDto> winners) {
        this.racingCars = cars;
        this.winners = winners.stream()
                .map(WinnerDto::getName)
                .collect(Collectors.joining(","));
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
