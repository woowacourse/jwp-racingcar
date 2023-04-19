package racingcar.domain;

import racingcar.RandomNumberGenerator;

import java.util.List;

public class RacingGame {

    private final RandomNumberGenerator numberGenerator;
    private final Cars cars;
    private final TryCount tryCount;

    public RacingGame(final RandomNumberGenerator numberGenerator, final Cars cars, final int tryCount) {
        this.cars = cars;
        this.tryCount = new TryCount(tryCount);
        this.numberGenerator = numberGenerator;
    }

    public void play() {
        while (!tryCount.isEnd()) {
            cars.moveAll(numberGenerator);
            tryCount.decreaseCount();
        }
    }

    public List<Car> decideWinners() {
        return cars.decideWinners();
    }

    public boolean isEnd() {
        return tryCount.isEnd();
    }

    public List<Car> getCars() {
        return cars.getUnmodifiableCars();
    }

    public int getTryCount() {
        return tryCount.getCount();
    }
}
