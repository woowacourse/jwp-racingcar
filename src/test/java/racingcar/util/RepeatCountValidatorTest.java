package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RepeatCountValidatorTest {

    private Validator repeatCountValidator;

    @DisplayName("반복 횟수 입력 실패 1미만 100초과")
    @ParameterizedTest
    @ValueSource(strings = {"0", "101", "-1"})
    void validateRange(int input) {
        repeatCountValidator = new RepeatCountValidator();

        assertThatThrownBy(() -> repeatCountValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시도할 횟수는 1~100 사이값만 가능합니다.");
    }

    @DisplayName("반복 횟수 입력 성공 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "100"})
    void validateRangeSuccess(int input) {
        repeatCountValidator = new RepeatCountValidator();
        assertThatNoException().isThrownBy(()->repeatCountValidator.validate(input));
    }
}
