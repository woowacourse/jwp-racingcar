package racingcar.utils;

import java.security.SecureRandom;

public class RandomNumberGenerator implements NumberGenerator {
    private static final int UPPER_BOUND = 10;
    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public int generateNumber() {
        return secureRandom.nextInt(UPPER_BOUND);
    }
}
