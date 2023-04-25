package racingcar.domain.strategy.move;

import java.util.Random;

public class RandomBasedMoveStrategy implements MoveStrategy {
    private static final Random RANDOM = new Random();
    private static final int UPPER_LIMIT_NUMBER = 10;
    private static final int MIN_MOVABLE_NUMBER = 4;
    
    @Override
    public boolean isMovable() {
        return isRandomNumberGreaterOrEqualToFour();
    }
    
    private boolean isRandomNumberGreaterOrEqualToFour() {
        return RANDOM.nextInt(UPPER_LIMIT_NUMBER) >= MIN_MOVABLE_NUMBER;
    }
}
