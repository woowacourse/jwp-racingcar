package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;

public class GameResultDto {

    private final String winners;
    private final List<Car> racingCars;

    public GameResultDto(final String winners, final List<Car> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public String getWinners() {
        return winners;
    }

    public List<Car> getRacingCars() {
        return racingCars;
    }
}
