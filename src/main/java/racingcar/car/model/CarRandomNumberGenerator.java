package racingcar.car.model;

import java.util.Random;

public class CarRandomNumberGenerator implements CarNumberGenerator {
    
    private static final int MAX_RANDOM_INT = 10;
    
    @Override
    public int generate() {
        final Random random = new Random();
        return random.nextInt(MAX_RANDOM_INT);
    }
}
