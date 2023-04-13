package racingcar;

import javax.annotation.Priority;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
@Priority(1)
public class AlwaysMoveNumberGenerator implements NumberGenerator {

    public static final int MOVE_NUMBER = 4;

    @Override
    public int generate() {
        return MOVE_NUMBER;
    }

}
