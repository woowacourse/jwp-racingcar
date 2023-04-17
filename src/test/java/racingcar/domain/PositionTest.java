package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PositionTest {
    @DisplayName("생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 9000000})
    void create(final int positionValue) {
        assertDoesNotThrow(() -> new Position(positionValue));
    }

    @DisplayName("한 칸씩 이동한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 9000000})
    void move(final int moveValue) {
        // given
        final Position position = new Position(moveValue);

        // when
        final Position movedPosition = position.move();

        // then
        assertThat(movedPosition).isEqualTo(new Position(moveValue + 1));
    }

    @DisplayName("동등성 검사가 가능하다.")
    @Test
    void equals() {
        // given
        final Position position = new Position(100);

        // expect
        assertThat(position).isEqualTo(new Position(100));
    }
}
