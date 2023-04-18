package racingcar.domain;

import racingcar.dto.response.CarGameResponse;
import racingcar.dto.response.CarResponse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    public static final String WINNER_DELIMITER = ",";
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

    public CarGameResponse play() {
        while (!isEnd()) {
            playTurn();
        }
        String winnerName = String.join(WINNER_DELIMITER, decideWinners());
        return new CarGameResponse(winnerName, getCarResults());
    }

    public void playTurn() {
        cars.moveAll(numberGenerator);
        tryCount.decreaseCount();
    }

    public List<String> decideWinners() {
        return cars.decideWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<CarResponse> getCarResults() {
        return cars.getUnmodifiableCars()
                .stream()
                .map(CarResponse::new)
                .collect(Collectors.toList());
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
