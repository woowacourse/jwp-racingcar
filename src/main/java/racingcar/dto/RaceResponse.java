package racingcar.dto;

import java.util.List;

public class RaceResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    private RaceResponse(final String winners, final List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RaceResponse create(final List<String> winners, final List<CarResponse> carRaceResult) {
        final String winnerNames = String.join(",", winners);
        return new RaceResponse(winnerNames, carRaceResult);
    }

    public static RaceResponse create(final String winners, final List<CarResponse> carResponses) {
        return new RaceResponse(winners, carResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
