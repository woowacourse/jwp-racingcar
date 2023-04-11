package racingcar.domain;

import racingcar.domain.numbergenerator.NumberGenerator;

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

    public Cars getCars() {
        return cars;
    }

    public int getGameTimeValue() {
        return gameTime.getGameTime();
    }
}
