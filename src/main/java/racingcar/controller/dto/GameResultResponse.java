package racingcar.controller.dto;

import racingcar.dto.CarDto;

import java.util.List;

public class GameResultResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    public GameResultResponse(final List<String> winners, final List<CarDto> racingCars) {
        this.winners = String.join(",", winners);
        this.racingCars = racingCars;
    }

    public GameResultResponse(final String winners, final List<CarDto> racingCars) {
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
