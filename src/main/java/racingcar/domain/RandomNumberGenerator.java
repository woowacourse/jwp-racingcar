package racingcar.domain;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private static final int DIGIT_MAX = 10;

    private final Random random = new Random();

    public int makeDigit() {
        return this.random.nextInt(DIGIT_MAX);
    }
}
