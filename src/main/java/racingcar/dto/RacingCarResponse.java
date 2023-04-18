package racingcar.dto;

import java.util.Collections;
import java.util.List;

public final class RacingCarResponse {
    private final List<String> winners;
    private final List<RacingCarDto> racingCars;

    public RacingCarResponse(final List<String> winners, final List<RacingCarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return Collections.unmodifiableList(winners);
    }

    public List<RacingCarDto> getRacingCars() {
        return Collections.unmodifiableList(racingCars);
    }
}
