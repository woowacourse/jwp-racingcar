package racingcar.service.dto;

import racingcar.dto.CarDto;

import java.util.List;

public class GameResult {

    private final String winners;
    private final List<CarDto> racingCars;

    public GameResult(final List<String> winners, final List<CarDto> racingCars) {
        this.winners = String.join(",", winners);
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
