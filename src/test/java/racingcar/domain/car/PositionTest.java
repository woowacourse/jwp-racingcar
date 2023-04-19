package racingcar.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PositionTest {
    @Test
    void 포지션이_1_증가한다() {
        Position position = new Position(3);
        assertThat(position.increase()).isEqualTo(new Position(4));
    }
}
