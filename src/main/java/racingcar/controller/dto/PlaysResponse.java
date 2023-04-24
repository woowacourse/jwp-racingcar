package racingcar.controller.dto;

import racingcar.domain.Car;
import racingcar.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaysResponse {
    private String winners;
    private List<CarResponse> racingCars;

    public PlaysResponse() {

    }

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

    public static PlaysResponse of(String winners, List<Player> players) {
        List<CarResponse> carResponses = new ArrayList<>();
        for (Player player : players) {
            String name = player.getName();
            int position = player.getPosition();
            CarResponse carResponse = CarResponse.of(new Car(name, position));
            carResponses.add(carResponse);
        }

        return new PlaysResponse(winners, carResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
