package racingcar.domain;

import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.List;

public class RacingGame {

    private final Cars cars;
    private final GameTime gameTime;

    public RacingGame(final Cars cars, final GameTime gameTime) {
        this.cars = cars;
        this.gameTime = gameTime;
    }

    public void play(final NumberGenerator numberGenerator) {
        while (gameTime.isPlayable()) {
            cars.moveCars(numberGenerator);
            gameTime.runOnce();
        }
    }

    public Winners winners() {
        return new Winners(cars);
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public int getGameTimeValue() {
        return gameTime.getGameTime();
    }
}
