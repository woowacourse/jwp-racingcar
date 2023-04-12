package racingcar.domain;

import racingcar.util.NumberGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class RaceNumberGenerator implements NumberGenerator {

    private static final int minNumber = 0;

    private static final int maxNumber = 9;

    @Override
    public int generate() {
        return minNumber + ThreadLocalRandom.current().nextInt(maxNumber - minNumber + 1);
    }
}
