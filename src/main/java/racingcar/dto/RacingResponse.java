package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;

public final class RacingResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    public RacingResponse(final String winners, final List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingResponse from(RacingResultDto racingResultDto) {
        String winners = racingResultDto.getWinners().stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(","));
        List<CarResponse> carResponses = racingResultDto.getRacingCars().stream()
                .map(carDto -> new CarResponse(carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toList());
        return new RacingResponse(winners, carResponses);
    }

    public String getWinners() {
        return this.winners;
    }

    public List<CarResponse> getRacingCars() {
        return this.racingCars;
    }
}
