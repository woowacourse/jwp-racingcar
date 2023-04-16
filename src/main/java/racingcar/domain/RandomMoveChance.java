package racingcar.domain;

import static racingcar.option.Option.MIN_MOVABLE_NUMBER;

import java.util.Random;

public class RandomMoveChance implements MoveChance {

    private static Random random = new Random();

    @Override
    public boolean isMovable() {
        return makeRandomNumber() < MIN_MOVABLE_NUMBER;
    }

    private int makeRandomNumber() {

        return random.nextInt(10);
    }
}
