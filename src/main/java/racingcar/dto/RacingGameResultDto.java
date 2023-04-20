package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameResultDto {
    private final String winners;

    private final List<CarDto> racingCars;

    public RacingGameResultDto(final RacingGame racingGame) {
        this.winners = racingGame.getWinners()
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        this.racingCars = racingGame.getCars()
                .stream()
                .map(CarDto::new)
                .collect(Collectors.toList());
    }

    public RacingGameResultDto(final String winners, final List<CarDto> racingCars) {
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
