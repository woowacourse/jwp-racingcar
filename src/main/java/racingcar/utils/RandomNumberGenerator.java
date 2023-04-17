package racingcar.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    private static final int RANDOM_NUMBER_RANGE = 10;

    private static final Random random = new Random();

    @Override
    public int generate() {
        return random.nextInt(RANDOM_NUMBER_RANGE);
    }
}
