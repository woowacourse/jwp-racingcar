package racingcar.domain;

import racingcar.domain.movingstrategy.MovingStrategy;

import java.util.List;
import java.util.stream.Collectors;

public final class RacingGame {

    private Long id;
    private Cars cars;
    private TryCount tryCount;

    public RacingGame(final Names carNames, final TryCount tryCount) {
        final List<Car> collect = carNames.getNames()
                .stream()
                .map(Car::new)
                .collect(Collectors.toList());

        this.cars = new Cars(collect);
        this.tryCount = tryCount;
    }

    public Cars run(final MovingStrategy strategy) {
        final int count = tryCount.getCount();
        for (int i = 0; i < count; i++) {
            moveCars(strategy);
        }
        return cars;
    }

    private void moveCars(final MovingStrategy strategy) {
        this.cars = cars.move(strategy);
        tryCount = tryCount.decreaseCount();
    }

    public int getTryCountValue() {
        return tryCount.getCount();
    }

    public Cars getWinners() {

        final List<Car> result = cars.getCars()
                .stream()
                .filter(Car::isWinner)
                .collect(Collectors.toList());
        return new Cars(result);
    }

    public List<Car> getCars() {
        return cars.getCars();
    }
}
