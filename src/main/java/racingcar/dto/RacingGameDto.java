package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Count;

public class RacingGameDto {

    private final List<Car> totalCars;
    private final List<Car> winners;
    private final Count count;

    public RacingGameDto(final List<Car> totalCars, final List<Car> winners, final Count count) {
        this.totalCars = totalCars;
        this.winners = winners;
        this.count = count;
    }

    public List<Car> getTotalCars() {
        return totalCars;
    }

    public List<Car> getWinners() {
        return winners;
    }

    public Count getCount() {
        return count;
    }
}
