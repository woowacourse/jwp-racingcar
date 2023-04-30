package racingcar.dto;

import java.util.List;

public class ResultResponse {
    private static final String SEPARATOR = ",";
    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public ResultResponse(final List<String> winners, final List<RacingCarResponse> racingCars) {
        this.winners = String.join(SEPARATOR, winners);
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponse> getRacingCars() {
        return racingCars;
    }
}
