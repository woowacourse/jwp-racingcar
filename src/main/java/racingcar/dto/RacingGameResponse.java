package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public final class RacingGameResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    public RacingGameResponse(final String winners, final List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingGameResponse from(GameResultDto gameResultDto) {
        String winners = gameResultDto.getWinners().stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(","));
        List<CarResponse> carResponses = gameResultDto.getRacingCars().stream()
                .map(carDto -> new CarResponse(carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toList());
        return new RacingGameResponse(winners, carResponses);
    }

    public String getWinners() {
        return this.winners;
    }

    public List<CarResponse> getRacingCars() {
        return this.racingCars;
    }
}
