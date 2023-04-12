package racingcar;

import racingcar.domain.Cars;

import java.util.List;
import java.util.stream.Collectors;

public class ResultDto {

    private final List<String> winners;
    private final List<CarDto> racingCars;

    public ResultDto(Cars winnersResult, Cars finalResult) {
        this.winners = winnersResult.getCars()
                .stream()
                .map(car -> car.getName().getName())
                .collect(Collectors.toList());
        this.racingCars = finalResult.getCars()
                .stream()
                .map(car -> new CarDto(car.getName().getName(), car.getPosition().getPosition()))
                .collect(Collectors.toList());
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
