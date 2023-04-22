package racingcar.controller.dto;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class GameResultReponse {
    private final String winners;
    private final List<CarStateResponse> racingCars;

    public GameResultReponse(final String winners, final List<CarStateResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResultReponse from(final List<Car> allCars, final List<Car> winnerCars) {
        String winners = winnerCars.stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));

        List<CarStateResponse> carStateResponses = allCars.stream()
                .map(car -> new CarStateResponse(car.getName(), car.getPosition()))
                .collect(Collectors.toList());

        return new GameResultReponse(winners, carStateResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStateResponse> getRacingCars() {
        return racingCars;
    }
}
