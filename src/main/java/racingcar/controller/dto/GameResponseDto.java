package racingcar.controller.dto;

import java.util.List;

public class GameResponseDto {

    private final String winners;
    private final List<CarResponseDto> racingCars;

    public GameResponseDto(String winners, List<CarResponseDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponseDto> getRacingCars() {
        return racingCars;
    }

}
