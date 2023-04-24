package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class PositionTest {

    @Test
    void INIT_메서드로_초기화() {
        final Position position = Position.init();
        assertThat(position.getMoveCount()).isZero();
    }

    @Test
    void NEXT_메서드로_증가된_Status_값객체_생성() {
        final Position position = Position.init();
        final Position movedPosition = position.next();
        assertThat(movedPosition.getMoveCount()).isEqualTo(1);
        assertThat(movedPosition).isNotEqualTo(position);
    }
}
