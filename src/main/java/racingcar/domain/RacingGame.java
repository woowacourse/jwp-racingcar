package racingcar.domain;

import racingcar.utils.MovingStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final Cars cars;
    private final TryCount tryCount;

    public RacingGame(List<Name> carNames, TryCount tryCount) {
        List<Car> collect = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());

        this.cars = new Cars(collect);
        this.tryCount = tryCount;
    }

    public Cars start(MovingStrategy strategy) {
        while (tryCount.canTry()) {
            cars.moveCars(strategy);
            tryCount.decreaseCount();
        }
        return new Cars(cars);
    }

    public List<Car> winners(List<Car> carList) {
        return cars.calculateWinners(carList);
    }
}
