package racingcar.domain;

import java.util.List;

public class RacingGame {

    private final Cars cars;
    private final Count count;
    private final GameId gameId;

    public RacingGame(final List<String> carsName, final int count) {
        gameId = new GameId();
        cars = Cars.from(carsName);
        this.count = new Count(count);
    }

    public RacingGame(final Integer gameId, final List<Car> cars, final int count) {
        this.gameId = new GameId(gameId);
        this.cars = new Cars(cars);
        this.count = new Count(count);
    }

    public void race(final NumberPicker numberPicker) {
        cars.race(count, numberPicker);
    }

    public Winners findWinner() {
        return new Winners(cars.findWinner());
    }

    public Count getCount() {
        return count;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public GameId getGameId() {
        return gameId;
    }
}
