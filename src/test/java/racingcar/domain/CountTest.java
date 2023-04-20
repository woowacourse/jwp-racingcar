package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.exception.TryCountException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CountTest {

    @DisplayName("0 이상의 정수는 count로 허용된다.")
    @Test
    void init_0_doesNotThrow() {
        assertThatCode(() -> new Count(0)).doesNotThrowAnyException();
    }

    @DisplayName("0 미만인 음수는 count로 허용되지 않는다")
    @Test
    void init_negative_throw() {
        assertThatThrownBy(() -> new Count(-1)).isInstanceOf(TryCountException.class);
    }
}
