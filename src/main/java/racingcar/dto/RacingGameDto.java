package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;
import racingcar.domain.race.RacingGame;

public class RacingGameDto {
    private List<String> winnerNames;
    private List<CarDto> racingCars;

    private RacingGameDto(List<Car> racingCars, List<Car> winnerNames) {
        this.winnerNames = convertToString(winnerNames);
        this.racingCars = convertToCarDto(racingCars);
    }

    public static RacingGameDto from(RacingGame game) {
        return new RacingGameDto(game.getRacingCars(), game.getWinners());
    }

    private List<String> convertToString(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private List<CarDto> convertToCarDto(List<Car> racingCars) {
        return racingCars.stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    public List<String> getWinnerNames() {
        return winnerNames;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }

}
