package racingcar.domain;

import racingcar.domain.engine.Engine;
import racingcar.domain.engine.RandomMovingEngine;
import racingcar.error.ErrorMessage;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final Cars cars;

    public RacingGame(List<Name> carNames) {
        validate(carNames);

        Engine engine = new RandomMovingEngine();
        List<Car> collect = carNames.stream()
                .map(name -> Car.of(name, engine))
                .collect(Collectors.toList());

        this.cars = Cars.from(collect);
    }

    private void validate(List<Name> carNames) {
        int countOfDistinctNames = (int) carNames.stream()
                .map(Name::getName)
                .distinct()
                .count();

        if (countOfDistinctNames == carNames.size()) {
            return;
        }

        throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NAMES.getValue());
    }

    public void moveCars(TryCount tryCount) {
        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveCars();
        }
    }

    public Cars decideWinners() {
        return cars.getWinners();
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
