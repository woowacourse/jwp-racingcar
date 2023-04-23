package racingcar.domain;

import java.util.List;

public class Winners {

    private final GameId gameId;
    private final List<Car> cars;

    public Winners(final GameId gameId, final List<Car> cars) {
        this.gameId = gameId;
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public GameId getGameId() {
        return gameId;
    }
}
