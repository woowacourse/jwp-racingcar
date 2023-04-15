package racingcar.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    public static final int RANDOM_NUMBER_BOUND = 10;

    private RandomNumberGenerator() {
    }

    public static RandomNumberGenerator makeInstance() {
        return new RandomNumberGenerator();
    }

    @Override
    public int generateNumber() {
        return new Random().nextInt(RANDOM_NUMBER_BOUND);
    }
}
