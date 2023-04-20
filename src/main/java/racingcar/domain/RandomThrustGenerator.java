package racingcar.domain;

import java.util.Random;

public class RandomThrustGenerator implements ThrustGenerator {
    private final int MAX_THRUST = 10;
    private final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(MAX_THRUST);
    }
}
