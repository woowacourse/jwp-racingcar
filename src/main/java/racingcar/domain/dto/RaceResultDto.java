package racingcar.domain.dto;

import java.util.List;

public class RaceResultDto {

    private final String winners;
    private final List<CarStatusDto> racingCars;

    private RaceResultDto(final String winners, final List<CarStatusDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RaceResultDto create(final List<String> winners, final List<CarStatusDto> carRaceResult) {
        final String winnerNames = String.join(",", winners);
        return new RaceResultDto(winnerNames, carRaceResult);
    }

    public List<CarStatusDto> getRacingCars() {
        return racingCars;
    }
}
