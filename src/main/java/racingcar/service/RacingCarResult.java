package racingcar.service;

import java.util.List;
import racingcar.domain.Car;

public class RacingCarResult {

    private final List<String> winners;
    private final List<Car> cars;
    private final Integer attempt;

    public RacingCarResult(final List<String> winners, final List<Car> cars, final Integer attempt) {
        this.winners = winners;
        this.cars = cars;
        this.attempt = attempt;
    }

    public RacingCarResult(final List<String> winners, final List<Car> cars) {
        this(winners, cars, null);
    }

    public List<String> getWinners() {
        return winners;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getAttempt() {
        return attempt;
    }
}
