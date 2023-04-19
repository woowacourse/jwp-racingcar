package racingcar.controller.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class ResultResponse {
    private final String winners;
    private final List<RacingCarResponse> racingCars;

    public ResultResponse(final String winners, final List<RacingCarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static ResultResponse from(final List<Car> allCars, final List<Car> winnerCars) {
        String winners = winnerCars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        List<RacingCarResponse> racingCarResponses = allCars.stream()
                .map(car -> new RacingCarResponse(car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        return new ResultResponse(winners, racingCarResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<RacingCarResponse> getRacingCars() {
        return racingCars;
    }
}
