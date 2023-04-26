package racingcar.dto;

import java.util.List;

public class PlayResponse {

    private final List<String> winners;
    private final List<RacingCarStatusDto> racingCars;

    private PlayResponse(final List<String> winners, final List<RacingCarStatusDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse of(final RacingCarWinnerDto winners, final List<RacingCarStatusDto> racingCars) {
        return new PlayResponse(winners.getWinners(), racingCars);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCarStatusDto> getRacingCars() {
        return racingCars;
    }
}
