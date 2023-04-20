package racingcar.controller.response;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    public RacingGameResponse(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = createCarDto(racingCars);
    }

    private List<CarResponse> createCarDto(final List<Car> racingCars) {
        return racingCars.stream()
                .map(car -> new CarResponse(car.getNameValue(), car.getPositionValue()))
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
