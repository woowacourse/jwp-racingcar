package racingcar.utils;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    private static final int RANDOM_NUMBER_BOUNDARY = 10;
    private final Random random = new Random();

    @Override
    public int generateNumber() {
        return random.nextInt(RANDOM_NUMBER_BOUNDARY);
    }
}
