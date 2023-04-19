package racingcar.dto.response;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    public GameResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static GameResponse of(String winners, List<Car> racingCars) {
        List<CarResponse> carResultRespons = racingCars.stream()
                .map(CarResponse::fromCar)
                .collect(Collectors.toList());
        return new GameResponse(winners, carResultRespons);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
