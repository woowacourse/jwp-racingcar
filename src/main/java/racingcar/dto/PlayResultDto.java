package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;

public class PlayResultDto {

    private final List<Car> racingCars;
    private final String winners;

    public PlayResultDto(final List<Car> racingCars, final String winners) {
        this.racingCars = racingCars;
        this.winners = winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }

    public String getWinners() {
        return winners;
    }
}
