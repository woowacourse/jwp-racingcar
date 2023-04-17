package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultResponse {
    private final String winners;
    private final List<CarDto> racingCars;

    private GameResultResponse(String winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResultResponse from(List<Car> winners, List<Car> racingCars) {
        String winnersToDto = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
        List<CarDto> racingCarsToDto = racingCars.stream()
                .map(CarDto::of)
                .collect(Collectors.toList());
        return new GameResultResponse(winnersToDto, racingCarsToDto);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
