package racingcar.dto;

import java.util.List;

public class RaceResponse {

    private static final String JOIN_DELIMITER = ",";
    private final String winners;
    private final List<CarStatusDto> racingCars;

    private RaceResponse(final String winners, final List<CarStatusDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RaceResponse create(final List<String> winners,
        final List<CarStatusDto> carRaceResult) {
        final String winnerNames = String.join(JOIN_DELIMITER, winners);
        return new RaceResponse(winnerNames, carRaceResult);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStatusDto> getRacingCars() {
        return racingCars;
    }
}
