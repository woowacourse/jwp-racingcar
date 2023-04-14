package racingcar.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import racingcar.domain.Car;

public class RacingInfoResponse {

    private String winners;
    private List<CarInfoDto> racingCars;

    public RacingInfoResponse(final String winners, final List<Car> racingCars) {
        this.winners = winners;
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
