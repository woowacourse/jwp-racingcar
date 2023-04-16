package racingcar.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;

public class RaceResultResponse {

    private final String winners;
    private final List<CarStatusResponse> racingCars;

    private RaceResultResponse(final String winners, final List<CarStatusResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RaceResultResponse create(String winners, List<Car> cars) {
        List<CarStatusResponse> carStatusResponses = mapToCarStatus(cars);
        return new RaceResultResponse(winners, carStatusResponses);
    }

    private static List<CarStatusResponse> mapToCarStatus(final List<Car> cars) {
        return cars.stream()
                .map(car -> new CarStatusResponse(car.getName(),
                        car.getPosition()))
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStatusResponse> getRacingCars() {
        return racingCars;
    }
}
