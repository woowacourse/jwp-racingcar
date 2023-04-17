package racingcar.controller.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameResponse {
    private final String winners;
    private final List<CarDto> racingCars;

    public RacingGameResponse(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = createCarDto(racingCars);
    }

    private List<CarDto> createCarDto(final List<Car> racingCars) {
        return racingCars.stream()
                .map(car -> new CarDto(car.getNameValue(), car.getPositionValue()))
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
