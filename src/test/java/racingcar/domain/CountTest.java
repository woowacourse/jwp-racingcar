package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {

    @ParameterizedTest(name = "{displayName} 시도횟수 : {0}")
    @ValueSource(ints = {0, -1, -100, -1000})
    @DisplayName("시도 횟수는 양수가 아니면 에러를 방생한다.")
    void throw_error_when_input_count_number_is_negative(int count) {
        assertThatThrownBy(() -> Count.of(count))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
