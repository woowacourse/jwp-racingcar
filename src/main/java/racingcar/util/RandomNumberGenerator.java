package racingcar.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    private static final int MAX_RANDOM_NUMBER = 10;

    private final Random random = new Random();

    @Override
    public int generateNumber() {
        return random.nextInt(MAX_RANDOM_NUMBER);
    }
}
