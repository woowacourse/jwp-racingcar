package racingcar.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    public static final int RANDOM_NUMBER_BOUND = 10;

    public RandomNumberGenerator() {
    }

    @Override
    public int generateNumber() {
        return new Random().nextInt(RANDOM_NUMBER_BOUND);
    }
}
