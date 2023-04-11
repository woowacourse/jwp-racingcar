package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class RacingResultDto {

    private final String winners;
    private final List<Car> racingCars;

    public RacingResultDto(List<Car> winners, List<Car> cars) {
        this.winners = winners.stream().map(Car::getName).collect(Collectors.joining(","));
        this.racingCars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
