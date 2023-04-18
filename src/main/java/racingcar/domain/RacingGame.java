package racingcar.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final RandomNumberGenerator numberGenerator;
    private final Cars cars;
    private final TryCount tryCount;
    private final Timestamp createdAt;

    public RacingGame(final RandomNumberGenerator numberGenerator, final Cars cars, final int tryCount) {
        this.cars = cars;
        this.tryCount = new TryCount(tryCount);
        this.numberGenerator = numberGenerator;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void play() {
        cars.moveAll(numberGenerator);
        tryCount.decreaseCount();
    }

    public List<String> decideWinners() {
        return cars.decideWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isEnd() {
        return tryCount.isEnd();
    }

    public Cars getCars() {
        return this.cars;
    }

    public int getTryCount() {
        return tryCount.getCount();
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
