package racingcar.config;

import racingcar.strategy.MovingStrategy;

public class FixedMovingStrategy implements MovingStrategy {

    @Override
    public int getRandomNumber() {
        return 5;
    }
}
