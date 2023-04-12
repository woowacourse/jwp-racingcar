package racingcar.domain;

import racingcar.RandomNumberGenerator;

public class CarRandomNumberGenerator implements RandomNumberGenerator {

    private static final int BOUND = 10;

    @Override
    public int generate() {
        return (int) (Math.random() * BOUND);
    }
}
