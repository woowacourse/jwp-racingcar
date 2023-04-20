package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.Cars;

public class GameResponseDto {
    private final List<Car> winners;
    private final List<Car> racingCars;

    public GameResponseDto(List<Car> winners, List<Car> racingCars) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public Cars getCars() {
        return Cars.of(racingCars);
    }

    public List<String> getWinners() {
        return winners.stream().map(winner -> winner.getName()).collect(Collectors.toList());
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
