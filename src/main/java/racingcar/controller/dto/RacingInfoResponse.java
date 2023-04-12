package racingcar.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.Name;

public class RacingInfoResponse {

    private String winners;
    private List<CarInfoDto> racingCars;

    public RacingInfoResponse() {
    }

    public RacingInfoResponse(final List<Name> winners, final List<Car> racingCars) {
        this.winners = winners.stream().map(Name::getName).collect(Collectors.joining(","));
        List<CarInfoDto> carInfoDtos = racingCars.stream()
                .map(car -> new CarInfoDto(car.getName().getName(), car.getPosition().getPosition()))
                .collect(Collectors.toList());
        this.racingCars = carInfoDtos;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarInfoDto> getRacingCars() {
        return racingCars;
    }
}
