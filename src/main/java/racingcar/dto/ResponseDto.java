package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseDto {
    private String winners;
    private List<Car> racingCars;

    public ResponseDto(List<Car> winners, List<Car> racingCars) {
        this.winners = winners.stream().map(car->car.getName()).collect(Collectors.joining(","));
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
