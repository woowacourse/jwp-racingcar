package racingcar.dto;

import java.util.List;
import racingcar.domain.RacingCar;

public class RacingCarResponse {
    private List<String> winners;
    private List<RacingCar> racingCars;

    public RacingCarResponse(final List<String> winners, final List<RacingCar> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<RacingCar> getRacingCars() {
        return racingCars;
    }
}
