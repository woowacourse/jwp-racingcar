package racingcar.dto;

import racingcar.vo.Trial;

import java.util.List;

public class RacingResultDto {
    private final Trial trial;
    private final List<String> winners;
    private final List<CarDto> racingCars;

    public RacingResultDto(Trial trial, List<String> winners, List<CarDto> racingCars) {
        this.trial = trial;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public Trial getTrial() {
        return trial;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public boolean isWinnerContaining(String name) {
        return winners.contains(name);
    }
}
