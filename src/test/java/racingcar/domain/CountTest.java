package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Count 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class CountTest {

    @Test
    void decrease_메서드는_횟수를_1_감소시킨다() {
        // given
        Count count = new Count(1);

        // when
        count.decrease();

        // then
        assertThat(count.isPlayable()).isFalse();
    }

    @ParameterizedTest(name = "isPlayable 메서드는 진행 가능 횟수가 {0}인 경우 {1}을 반환한다.")
    @CsvSource({"1,true", "0,false"})
    void isPlayable_메서드는_진행_가능_여부를_반환한다(final int value, final boolean state) {
        // given
        Count count = new Count(value);

        // expect
        assertThat(count.isPlayable()).isEqualTo(state);
    }
}
