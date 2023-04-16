package racingcar.dto.response;

import java.util.List;

public class CarGameResponse {
    private final String winners;
    private final List<CarResponse> racingCars;

    public CarGameResponse(String winners, List<CarResponse> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarResponse> getRacingCars() {
        return racingCars;
    }
}
