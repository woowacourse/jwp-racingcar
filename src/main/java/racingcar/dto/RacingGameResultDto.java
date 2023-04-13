package racingcar.dto;

import java.util.List;

public class RacingGameResultDto {
    private final String winners;

    private final int playCount;

    private final List<RacingCarDto> racingCars;

    public RacingGameResultDto(final String winners, final int playCount, final List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.playCount = playCount;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarDto> getRacingCars() {
        return racingCars;
    }

    public int getPlayCount() {
        return playCount;
    }
}
