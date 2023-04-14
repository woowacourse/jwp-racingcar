package racingcar.domain;

import java.security.SecureRandom;

public class RandomNumberPicker implements NumberPicker {

    private static final int MAX_NUMBER = 10;

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public int pickNumber() {
        return secureRandom.nextInt(MAX_NUMBER);
    }
}
