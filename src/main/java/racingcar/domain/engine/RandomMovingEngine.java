package racingcar.domain.engine;

import static racingcar.utils.RandomPowerGenerator.createRandomPower;

public class RandomMovingEngine implements Engine {
    private final static int MOVE_STANDARD = 4;

    @Override
    public boolean isMovable() {
        return createRandomPower() >= MOVE_STANDARD;
    }
}
