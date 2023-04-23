package racingcar.domain;

import java.util.List;

public class Winners {

    private final List<Car> cars;

    public Winners(final List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
