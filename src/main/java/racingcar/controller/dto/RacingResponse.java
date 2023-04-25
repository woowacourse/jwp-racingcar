package racingcar.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.dto.CarResponse;

public final class RacingResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    public RacingResponse(final String winners, final List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RacingResponse from(RacingGame racingGame) {
        String winners = racingGame.getWinners().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(","));
        List<CarResponse> carResponses = racingGame.getCars().stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
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
