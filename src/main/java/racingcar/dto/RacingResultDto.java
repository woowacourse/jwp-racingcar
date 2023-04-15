package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class RacingResultDto {

    private final String winners;
    private final List<Car> racingCars;

    public RacingResultDto(List<Car> winners, List<Car> cars) {
        this.winners = initWinners(winners);
        this.racingCars = cars;
    }

    private String initWinners(List<Car> winners) {
        return winners.stream()
            .map(Car::getName)
            .collect(Collectors.joining(","));
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
