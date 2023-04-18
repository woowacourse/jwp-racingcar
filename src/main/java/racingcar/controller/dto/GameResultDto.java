package racingcar.controller.dto;

import java.util.List;

public class GameResultDto {

    private final String winners;
    private final List<RacingCarDto> racingCars;

    public GameResultDto(String winners, List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "GameResultDto{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
