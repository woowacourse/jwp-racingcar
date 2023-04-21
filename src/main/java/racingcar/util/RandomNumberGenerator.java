package racingcar.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomNumberGenerator implements NumberGenerator {

    private static final int MAX_RANDOM_INT = 10;

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public int generate() {
        return random.nextInt(MAX_RANDOM_INT);
    }
}
