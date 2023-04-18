package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public final class RacingGameResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    public RacingGameResponse(final String winners, final List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingGameResponse from(GameResultDto gameResultDto) {
        String winners = gameResultDto.getWinners().stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(","));
        return new RacingGameResponse(winners, gameResultDto.getRacingCars());
    }

    public String getWinners() {
        return this.winners;
    }

    public List<CarDto> getRacingCars() {
        return this.racingCars;
    }
}
