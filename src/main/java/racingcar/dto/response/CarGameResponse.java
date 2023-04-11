package racingcar.dto.response;

import java.util.List;

public class CarGameResponse {
    private final String winners;
    private final List<CarDto> racingCars;

    public CarGameResponse(String winners, List<CarDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<CarDto> getRacingCars() {
        return racingCars;
    }
}
