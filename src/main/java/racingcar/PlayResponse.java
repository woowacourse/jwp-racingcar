package racingcar;

import java.util.List;
import racingcar.dto.RacingCarStatusResponse;
import racingcar.dto.RacingCarWinnerResponse;

public class PlayResponse {

    private final List<String> winners;
    private final List<RacingCarStatusResponse> racingCars;

    private PlayResponse(final List<String> winners, final List<RacingCarStatusResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlayResponse of(final RacingCarWinnerResponse winners, final List<RacingCarStatusResponse> racingCars) {
        return new PlayResponse(winners.getWinners(), racingCars);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCarStatusResponse> getRacingCars() {
        return racingCars;
    }
}
