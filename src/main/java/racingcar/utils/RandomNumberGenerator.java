package racingcar.utils;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    private final Random random = new Random();

    @Override
    public int generateNumber() {
        return random.nextInt(10);
    }
}
