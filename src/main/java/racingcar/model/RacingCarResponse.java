package racingcar.model;

import java.util.List;

public class RacingCarResponse {
    private final String winners;
    private final List<Car> racingCars;

    public RacingCarResponse(final RacingCarResult racingCarResult) {
        this.winners = String.join(",", racingCarResult.getWinners());
        this.racingCars = racingCarResult.getRacingCars();
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
