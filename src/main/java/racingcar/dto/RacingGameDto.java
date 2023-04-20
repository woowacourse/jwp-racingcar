package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameDto implements PlayResultDto {
    private final String winners;

    private final int playCount;

    private final List<CarDto> racingCars;

    public RacingGameDto(final RacingGame racingGame) {
        this.winners = racingGame.getWinners()
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        this.playCount = racingGame.getPlayCount();

        this.racingCars = racingGame.getCars()
                .stream()
                .map(CarDto::new)
                .collect(Collectors.toList());
    }

    public RacingGameDto(final String winners, final int playCount, final List<CarDto> racingCars) {
        this.winners = winners;
        this.playCount = playCount;
        this.racingCars = racingCars;
    }

    @Override
    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public int getPlayCount() {
        return playCount;
    }
}
