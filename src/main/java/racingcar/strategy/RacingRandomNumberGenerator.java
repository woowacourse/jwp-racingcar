package racingcar.strategy;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RacingRandomNumberGenerator implements RacingNumberGenerator {

    private static final int LOWER_NUMBER = 0;
    private static final int UPPER_NUMBER = 9;

    @Override
    public int generate() {
        return LOWER_NUMBER + ThreadLocalRandom.current().nextInt(UPPER_NUMBER - LOWER_NUMBER + 1);
    }
}
