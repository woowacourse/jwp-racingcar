package racing.domain.utils;

import java.security.SecureRandom;

public class RandomRacingCarNumberGenerator implements RacingCarNumberGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final int RACING_CAR_NUMBER_MAX = 10;

    @Override
    public int peekNumber() {
        return random.nextInt(RACING_CAR_NUMBER_MAX);
    }
}
