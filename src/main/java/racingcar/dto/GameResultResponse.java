package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultResponse {
    private final String winners;
    private final List<CarForNameAndPosition> racingCars;

    public GameResultResponse(List<Car> winners, List<Car> racingCars) {
        String winnersToDto = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
        List<CarForNameAndPosition> racingCarsToDto = racingCars.stream()
                .map(CarForNameAndPosition::new)
                .collect(Collectors.toList());
        this.winners = winnersToDto;
        this.racingCars = racingCarsToDto;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarForNameAndPosition> getRacingCars() {
        return racingCars;
    }
}
