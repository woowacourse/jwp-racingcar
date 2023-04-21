package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResponseDto {
    private final List<Car> winners;
    private final List<Car> racingCars;

    public PlayResponseDto(List<Car> winners, List<Car> racingCars) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<String> getWinners() {
        return winners
                .stream()
                .map(winner -> winner.getName())
                .collect(Collectors.toList());
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
