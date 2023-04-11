package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;

public class GameResultDto {

    private final List<Car> racingCars;
    private final String winners;

    public GameResultDto(final List<Car> racingCars, final String winners) {
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
