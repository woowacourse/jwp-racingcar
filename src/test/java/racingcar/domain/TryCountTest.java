package racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TryCountTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, -100})
    @DisplayName("양의 정수가 아닐 경우 Exception 발생")
    void validateNotPositiveInteger(final int tryCount) {
        //when
        //then
        assertThatThrownBy(() -> new TryCount(tryCount));
    }
}
