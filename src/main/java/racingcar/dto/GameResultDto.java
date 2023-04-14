package racingcar.dto;

import racingcar.domain.Car;
import racingcar.domain.Winner;
import racingcar.domain.Winners;

import java.util.List;
import java.util.stream.Collectors;

public final class GameResultDto {

    private final String winners;
    private final List<CarDto> racingCars;

    public GameResultDto(final List<Car> cars, final Winners winners) {
        this.racingCars = cars.stream()
                .map(CarDto::new)
                .collect(Collectors.toList());
        this.winners = winners.getWinners().stream()
                .map(Winner::getName)
                .collect(Collectors.joining(","));
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
