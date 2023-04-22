package racingcar.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RacingGame {
    private final NumberGenerator numberGenerator;
    private final Cars cars;
    private final TryCount tryCount;
    private final Timestamp createdAt;

    public RacingGame(final NumberGenerator numberGenerator, final Cars cars, final TryCount tryCount) {
        this.cars = cars;
        this.tryCount = tryCount;
        this.numberGenerator = numberGenerator;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void play() {
        while (tryCount.canTry()) {
            playTurn();
            tryCount.decrease();
        }
    }

    private void playTurn() {
        cars.moveAll(numberGenerator);
    }

    public List<String> getWinnerNames() {
        return cars.getMaxPositionCars().stream()
                .map(Car::getName)
                .collect(toList());
    }

    public List<Car> getCars() {
        return this.cars.getUnmodifiableCars();
    }

    public int getTryCount() {
        return tryCount.getCount();
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
