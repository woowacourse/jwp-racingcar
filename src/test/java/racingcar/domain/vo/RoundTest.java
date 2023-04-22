package racingcar.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class RoundTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("1 미만의 숫자가 입력된 경우 예외가 발생한다.")
    void validateTest(int input) {
        assertThatThrownBy(() -> new Round(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("값이 같은 두 Round 객체는 동일한 객체다")
    void valueTest() {
        final int tryCount = 1;
        final Round round1 = new Round(tryCount);
        final Round round2 = new Round(tryCount);

        assertThat(round1.equals(round2)).isTrue();
        assertThat(round1.getValue()).isSameAs(round2.getValue());
        assertThat(new HashSet<>(List.of(round1, round2))).hasSize(1);
    }
}
