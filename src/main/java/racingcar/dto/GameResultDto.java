package racingcar.dto;


import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultDto {
    private final String winners;
    private final List<CarDto> racingCars;

    public GameResultDto(String winners, List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars.stream().map(car -> new CarDto(car.getName(), car.getPosition())).collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

    @Override
    public String toString() {
        return "GameResponseDto{" +
                "winners='" + winners + '\'' +
                ", racingCars=" + racingCars +
                '}';
    }
}
