package racingcar.domain;

import racingcar.domain.movingstrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class RacingGame {

    private final Cars cars;
    private TryCount tryCount;

    public RacingGame(final List<Name> carNames, final TryCount tryCount) {
        final List<Car> collect = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());

        this.cars = new Cars(collect);
        this.tryCount = tryCount;
    }

    public List<Cars> start(final MovingStrategy strategy) {
        final List<Cars> movingStatus = new ArrayList<>();
        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveCars(strategy);
            movingStatus.add(new Cars(cars));
            tryCount = tryCount.decreaseCount();
        }
        return movingStatus;
    }

    public Cars decideWinners() {
        return cars.getWinners();
    }

}
