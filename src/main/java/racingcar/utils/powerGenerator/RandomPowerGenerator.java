package racingcar.utils.powerGenerator;

import java.util.Random;

public class RandomPowerGenerator implements PowerGenerator {
    private static final int UPPER_BOUND = 10;
    private static final Random random = new Random();


    @Override
    public int generate() {
        return random.nextInt(UPPER_BOUND);
    }
}
