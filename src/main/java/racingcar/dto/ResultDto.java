package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

public class ResultDto {

    private String winners;
    private List<CarDto> racingCars;

    public ResultDto(List<Car> racingCars, List<Car> winners) {
        this.winners = convertToString(winners);
        this.racingCars = convertToCarDto(racingCars);
    }

    private String convertToString(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> convertToCarDto(List<Car> racingCars) {
        return racingCars.stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

}
