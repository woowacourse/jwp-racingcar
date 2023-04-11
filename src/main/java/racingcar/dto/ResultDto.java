package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

public class ResultDto {

    private String winners;
    private List<CarDto> racingCars;

    public ResultDto(List<Car> winningCars, List<Car> racingCars) {
        this.winners = convertToString(winningCars);
        this.racingCars = convertToDto(racingCars);
    }

    private String convertToString(List<Car> winningCars) {
        return winningCars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarDto> convertToDto(List<Car> cars) {
        return cars.stream()
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
