package racingcar.controller;

import java.util.List;

import racingcar.domain.Car;

public class GameResponseDto {

    private final String winners;
    private final List<Car> racingCars;

    public GameResponseDto(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
