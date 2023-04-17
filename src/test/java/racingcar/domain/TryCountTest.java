package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TryCountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -100})
    @DisplayName("양의 정수가 아닐 경우 Exception 발생")
    void validateNotPositiveInteger(final int tryCount) {
        //when
        //then
        assertThatThrownBy(() -> new TryCount(tryCount));
    }

    @Test
    @DisplayName("TryCount 1에서 차감을 한번 하면 0이다.")
    void deductAndValidateZero() {
        // given
        final TryCount tryCount = new TryCount(1);

        // when
        tryCount.deduct();

        // then
        assertThat(tryCount.isZero()).isTrue();
    }
}
