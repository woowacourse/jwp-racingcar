package racingcar.domain;

import racingcar.exception.ExceptionInformation;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomPicker implements Pickable {

    private static final int RANDOM_NUMBER_UPPER_BOUND = 10;

    private static final Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(ExceptionInformation.CANNOT_CREATE_RANDOM.getExceptionMessage());
        }
    }

    @Override
    public int pickNumber() {
        return random.nextInt(RANDOM_NUMBER_UPPER_BOUND);
    }
}
