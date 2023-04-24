package racingcar.dto;

import java.util.List;

public class HistoryResponse {
    private final String winners;
    private final List<RacingCarStatusDto> racingCars;

    public HistoryResponse(final String winners, final List<RacingCarStatusDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarStatusDto> getRacingCars() {
        return racingCars;
    }
}
