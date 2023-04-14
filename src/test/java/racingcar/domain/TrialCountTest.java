package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("시도 횟수는 ")
class TrialCountTest {

    @ParameterizedTest(name = "값이 0 이하로 들어오면 예외가 발생한다")
    @ValueSource(ints = {-1, 0})
    void 값이_0_이하로_들어오면_예외가_발생한다(final int inputValue) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new TrialCount(inputValue)
        );
    }

    @ParameterizedTest(name = "값이 1 이상으로 들어오면 정상 생성된다")
    @ValueSource(ints = {1, 99, 100})
    void 값이_1_이상으로_들어오면_정상_생성된다(final int inputValue) {
        assertThatNoException().isThrownBy(
                () -> new TrialCount(inputValue)
        );
    }
}
