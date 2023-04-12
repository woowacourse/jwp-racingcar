package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public ResponseDto(List<Car> winners, List<Car> racingCars) {
        this.winners = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
        this.racingCars = racingCars.stream()
                .map(CarDto::createCarDto)
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
