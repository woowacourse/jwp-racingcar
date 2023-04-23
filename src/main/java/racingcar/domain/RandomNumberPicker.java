package racingcar.domain;

import java.security.SecureRandom;

public class RandomNumberPicker implements NumberPicker {

    private static final int RANDOM_NUM_MAX_VALUE = 10;

    private static final RandomNumberPicker INSTANCE = new RandomNumberPicker();
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private RandomNumberPicker() {
    }

    public static RandomNumberPicker getInstance() {
        return INSTANCE;
    }

    @Override
    public int pickNumber() {
        return SECURE_RANDOM.nextInt(RANDOM_NUM_MAX_VALUE);
    }
}
