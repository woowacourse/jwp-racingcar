package racingcar.domain;

import racingcar.RandomNumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final RandomNumberGenerator numberGenerator;
    private final Cars cars;
    private final TryCount tryCount;

    private boolean end;

    public RacingGame(final RandomNumberGenerator numberGenerator, final Cars cars, final int tryCount) {
        this.cars = cars;
        this.tryCount = new TryCount(tryCount);
        this.numberGenerator = numberGenerator;
        this.end = false;
    }

    public void play() {
        for (int i = 0; i < tryCount.getCount(); i++) {
            cars.moveAll(numberGenerator);
        }
        this.end = true;
    }

    public List<String> decideWinners() {
        return cars.decideWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isEnd() {
        return end;
    }

    public Cars getCars() {
        return this.cars;
    }

    public int getTryCount() {
        return tryCount.getCount();
    }
}
