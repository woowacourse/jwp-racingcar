package racingcar.dto.response;

import racingcar.domain.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarGameResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    public CarGameResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static CarGameResponse of(String winners, List<Car> racingCars) {
        List<CarResponse> carResponses = racingCars.stream()
                .map(CarResponse::fromCar)
                .collect(Collectors.toList());
        return new CarGameResponse(winners, carResponses);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
