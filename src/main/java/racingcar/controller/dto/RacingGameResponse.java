package racingcar.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;

public class RacingGameResponse {

    private String winners;
    private List<CarDto> racingCars;

    public RacingGameResponse(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        List<CarDto> carDtos = racingCars.stream()
                .map(car -> new CarDto(car.getName().getName(), car.getPosition().getPosition()))
                .collect(Collectors.toList());
        this.racingCars = carDtos;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
