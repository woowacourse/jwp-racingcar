package racingcar.service;

import java.util.List;
import racingcar.model.Car;

public class RacingResponse {

    private final String winners;
    private final List<Car> racingCars;

    public RacingResponse(final String winners, final List<Car> cars) {
        this.winners = winners;
        this.racingCars = cars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

}
