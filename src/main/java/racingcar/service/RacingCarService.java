package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarService {

    private final Cars cars;
    private final int count;
    private final NumberGenerator numberGenerator;

    public RacingCarService(Cars cars, int count,NumberGenerator numberGenerator) {
        this.cars = cars;
        this.count = count;
        this.numberGenerator = numberGenerator;
    }

    public void move() {
        for (int i = 0; i < count; i++) {
            cars.moveAll(numberGenerator);
        }
    }

    public String getWinners() {
        return cars.pickWinners().getAll().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public List<Car> getCars() {
        return cars.getAll();
    }
}
