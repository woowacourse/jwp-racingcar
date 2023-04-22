package racingcar.utils;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    public static final int RANDOM_NUMBER_BOUND = 10;

    public RandomNumberGenerator() {
    }

    @Override
    public int generateNumber() {
        return new Random().nextInt(RANDOM_NUMBER_BOUND);
    }
}
