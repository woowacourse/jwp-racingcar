package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;

public class RacingGameResponse {

    private final String winners;
    private final List<CarDto> racingCars;

    public RacingGameResponse(List<String> winners, List<CarDto> racingCars) {
        this.winners = String.join(",", winners);
        this.racingCars = racingCars;
    }

    public static RacingGameResponse of(RacingGame racingGame) {
        List<Car> winners = racingGame.findWinners();
        List<CarDto> carDtos = racingGame.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition(), winners.contains(car)))
                .collect(Collectors.toList());
        return new RacingGameResponse(getWinnerNames(winners), carDtos);
    }

    private static List<String> getWinnerNames(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
