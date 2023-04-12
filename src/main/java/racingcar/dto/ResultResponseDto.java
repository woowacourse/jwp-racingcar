package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;

public class ResultResponseDto {

    private final String winners;
    private final List<Car> racingCars;

    public ResultResponseDto(String winners, List<Car> racingCars) {
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
