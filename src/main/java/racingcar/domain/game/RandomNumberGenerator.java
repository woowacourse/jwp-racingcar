package racingcar.domain.game;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator implements NumberGenerator {
    Random random = new Random();
    public static final int BOUND = 10;

    @Override
    public int generateNumber() {
        return random.nextInt(BOUND);
    }
}
