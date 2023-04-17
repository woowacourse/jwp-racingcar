package racingcar.domain;

import java.util.Random;

public class RandomThrustGenerator implements ThrustGenerator {

    private final int MAX_THRUST = 10;

    @Override
    public int generate() {
        return new Random().nextInt(MAX_THRUST);
    }
}
