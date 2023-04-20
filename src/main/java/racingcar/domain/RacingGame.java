package racingcar.domain;

import racingcar.domain.engine.RandomMovingEngine;
import racingcar.dto.CarDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final Cars cars;

    public RacingGame(String names) {
        List<Name> carNames = convert2Names(names);
        this.cars = new Cars(carNames, new RandomMovingEngine());
    }

    public RacingGame(List<Car> cars) {
        this.cars = new Cars(cars);
    }

    public void moveCars(int count) {
        TryCount tryCount = new TryCount(count);
        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveCars();
        }
    }

    public String decideWinners() {
        return cars.getWinners();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    private List<Name> convert2Names(String names) {
        return Arrays.stream(names.split(","))
                .map(Name::new)
                .collect(Collectors.toList());
    }
}
