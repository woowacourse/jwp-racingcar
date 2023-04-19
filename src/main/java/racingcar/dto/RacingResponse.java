package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public class RacingResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    public RacingResponse(final List<CarDto> winnerCars, final List<CarDto> racingCars) {
        this.winners = makeWinners(winnerCars);
        this.racingCars = racingCars;
    }

    private String makeWinners(final List<CarDto> winners) {
        return winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(", "));
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
