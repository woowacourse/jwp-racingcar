package racingcar.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class PlaysResponse {

    private final String winners;
    private final List<CarResponse> racingCars;

    private PlaysResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static PlaysResponse of(List<Car> winners, List<Car> racingCars) {
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
        String winnerResponse = String.join(",", winnerNames);

        List<CarResponse> carResponses = racingCars.stream()
                .map(CarResponse::of)
                .collect(Collectors.toList());

        return new PlaysResponse(winnerResponse, carResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
