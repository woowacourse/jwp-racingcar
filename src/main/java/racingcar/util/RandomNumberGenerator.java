package racingcar.util;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    private static final int RANDOM_NUMBER_STANDARD = 10;
    private static Random random = new Random();

    @Override
    public int generateNumber() {
        return random.nextInt(RANDOM_NUMBER_STANDARD);
    }
}
