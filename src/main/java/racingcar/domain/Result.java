package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class Result {
    private final List<Car> winners;
    private final List<Car> cars;

    public Result(final List<Car> winners, final List<Car> cars) {
        this.winners = winners;
        this.cars = cars;
    }

    public List<Car> winners() {
        return Collections.unmodifiableList(winners);
    }

    public List<Car> cars() {
        return Collections.unmodifiableList(cars);
    }
}
