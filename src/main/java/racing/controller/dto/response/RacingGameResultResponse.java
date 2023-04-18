package racing.controller.dto.response;

import racing.domain.Cars;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameResultResponse {

    private List<String> winners;
    private List<CarResponse> racingCars;

    public RacingGameResultResponse() {
    }

    public RacingGameResultResponse(List<String> winners, List<CarResponse> carResponses) {
        this.winners = winners;
        this.racingCars = carResponses;
    }

    public static RacingGameResultResponse of(List<String> winners, Cars cars) {
        List<CarResponse> carsResponses = cars.getCars().stream()
                .map(car -> new CarResponse(car.getName(), car.getStep()))
                .collect(Collectors.toList());
        return new RacingGameResultResponse(winners, carsResponses);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }

}
