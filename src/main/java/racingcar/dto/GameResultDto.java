package racingcar.dto;

import java.util.List;

public class GameResultDto {
    private final int trialCount;
    private final String winners;
    private final List<CarDto> racingCars;

    public GameResultDto(int trialCount, String winners, List<CarDto> racingCars) {
        this.trialCount = trialCount;
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
