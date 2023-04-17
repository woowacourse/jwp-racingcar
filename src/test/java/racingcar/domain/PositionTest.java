package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Position 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PositionTest {

    @Test
    void Position의_초기_위치값은_0이다() {
        // given
        Position position = new Position();

        // expect
        assertThat(position.getValue()).isEqualTo(0);
    }

    @Test
    void increase_메서드는_위치_값을_1_증가시킨다() {
        // given
        Position position = new Position();

        // when
        position.increase();

        // then
        assertThat(position.getValue()).isEqualTo(1);
    }
}
