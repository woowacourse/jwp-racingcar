package racingcar.domain;

import java.util.Random;

import static racingcar.option.Option.MIN_MOVABLE_NUMBER;

public class RandomMoveChance implements MoveChance {

    @Override
    public boolean isMovable() {
        return makeRandomNumber() < MIN_MOVABLE_NUMBER;
    }

    private int makeRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
