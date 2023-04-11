package racingcar.domain;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import racingcar.exception.CannotCreateRandomException;

public class RandomPicker implements Pickable {

    private static final int RANDOM_NUMBER_UPPER_BOUND = 10;

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new CannotCreateRandomException();
        }
    }

    @Override
    public int pickNumber() {
        return random.nextInt(RANDOM_NUMBER_UPPER_BOUND);
    }
}
