package racingcar.domain;

import racingcar.util.NumberGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final Long gameId;
    private final Cars cars;
    private final int count;

    public RacingGame(Cars cars, int count) {
        this.gameId = null;
        this.cars = cars;
        this.count = count;
    }

    public RacingGame(Long gameId, RacingGame racingGame) {
        this.gameId = gameId;
        this.cars = racingGame.cars;
        this.count = racingGame.count;
    }

    public void moveCars(NumberGenerator numberGenerator) {
        for (int i = 0; i < count; i++) {
            cars.moveAll(numberGenerator);
        }
    }

    public String getWinners() {
        return cars.getWinners().stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
    }

    public Long getGameId() {
        return gameId;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public int getCount() {
        return count;
    }
}
