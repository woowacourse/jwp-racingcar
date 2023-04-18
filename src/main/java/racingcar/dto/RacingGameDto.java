package racingcar.dto;

import java.util.List;

public class RacingGameDto {
    private final String winners;

    private final int playCount;

    private final List<PlayerResultDto> racingCars;

    public RacingGameDto(final String winners, final int playCount, final List<PlayerResultDto> racingCars) {
        this.winners = winners;
        this.playCount = playCount;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<PlayerResultDto> getRacingCars() {
        return racingCars;
    }

    public int getPlayCount() {
        return playCount;
    }
}
