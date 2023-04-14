package racingcar.domain;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class RaceNumberGenerator implements NumberGenerator {

    private static final int minNumber = 0;
    private static final int maxNumber = 9;

    @Override
    public int generate() {
        return minNumber + ThreadLocalRandom.current().nextInt(maxNumber - minNumber + 1);
    }
}
