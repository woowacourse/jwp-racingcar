package racingcar.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RacingGameMoveStrategy implements MoveStrategy {

    private static final int RANDOM_NUMBER_COUNT = 10;
    private static final int MOVE_CONDITION = 4;
    private final Random random = new Random();

    @Override
    public Action getMovement() {
        int randomNumber = random.nextInt(RANDOM_NUMBER_COUNT);
        if (randomNumber >= MOVE_CONDITION) {
            return Action.MOVE;
        }
        return Action.STAY;
    }
}
