package racingcar.controller;

import java.util.List;

import racingcar.domain.Car;

public class ResponseDto {
    private final String winners;
    private final List<Car> racingCars;

    public ResponseDto(String winners, List<Car> racingCars) {
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
