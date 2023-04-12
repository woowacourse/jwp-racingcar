package racingcar.service;

import racingcar.domain.Cars;
import racingcar.util.NumberGenerator;

public class RacingCarService {

    private final Cars cars;
    private final NumberGenerator numberGenerator;

    public RacingCarService(Cars cars, NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
    }

    public void move() {
        cars.moveAll(numberGenerator);
    }

    public Cars getWinners() {
        return cars.pickWinners();
    }
}
