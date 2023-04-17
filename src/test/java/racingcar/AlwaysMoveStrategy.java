package racingcar;

import org.springframework.boot.test.context.TestComponent;
import racingcar.domain.Action;
import racingcar.domain.MoveStrategy;

import javax.annotation.Priority;

@TestComponent
@Priority(1)
public class AlwaysMoveStrategy implements MoveStrategy {


    @Override
    public Action getMovement() {
        return Action.MOVE;
    }
}
