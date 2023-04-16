package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultResponseDto {
    private final String winners;
    private final List<CarDto> racingCars;

    private GameResultResponseDto(String winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResultResponseDto createGameResultResponseDto(List<Car> winners, List<Car> racingCars) {
        String winnersToDto = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
        List<CarDto> racingCarsToDto = racingCars.stream()
                .map(CarDto::createCarDto)
                .collect(Collectors.toList());
        return new GameResultResponseDto(winnersToDto, racingCarsToDto);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
