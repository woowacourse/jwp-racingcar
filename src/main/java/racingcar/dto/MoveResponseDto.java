package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class MoveResponseDto {
    private final List<CarDto> winners;
    private final List<CarDto> racingCars;

    public MoveResponseDto(List<Car> winners, List<Car> racingCars) {
        this.winners = winners.stream()
                .map(CarDto::createCarDto)
                .collect(Collectors.toList());
        this.racingCars = racingCars.stream()
                .map(CarDto::createCarDto)
                .collect(Collectors.toList());
    }

    public List<CarDto> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
