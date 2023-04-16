package racingcar.dto;

import java.util.List;

import racingcar.domain.Car;

public class PlayResultResponseDto {
    private final String winners;
    private final List<Car> racingCars;

    public PlayResultResponseDto(String winners, List<Car> racingCars) {
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
