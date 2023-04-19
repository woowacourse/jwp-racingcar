package racingcar.domain;

import racingcar.dto.response.GameResponse;
import racingcar.dto.response.CarResponse;

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
        while (tryCount.canTry()) {
            playTurn();
            tryCount.decrease();
        }
    }

    private void playTurn() {
        cars.moveAll(numberGenerator);
    }

    public GameResponse getCarGameResult() {
        return new GameResponse(getWinnerNames(), getCarResults());
    }

    public String getWinnerNames() {
        Cars maxPositionCars = new Cars(cars.getMaxPositionCars());
        return maxPositionCars.getCombinedNames();
    }

    private List<CarResponse> getCarResults() {
        return cars.getUnmodifiableCars()
                .stream()
                .map(CarResponse::fromCar)
                .collect(Collectors.toList());
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
