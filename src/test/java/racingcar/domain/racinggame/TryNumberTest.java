package racingcar.domain.racinggame;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TryNumberTest {
    @ParameterizedTest(name = "{displayName} : tryNumber = {0}")
    @ValueSource(ints = {0, 1_000_000})
    void 숫자가_1이상_100만_이하인_경우_예외가_발생하지_않는다(int tryNumber) {
        assertThatNoException()
                .isThrownBy(() -> new TryNumber(tryNumber));
    }
    
    @ParameterizedTest(name = "{displayName} : tryNumber = {0}")
    @ValueSource(ints = {-1, 1_000_001})
    void 숫자가_1미만_또는_100만_초과인_경우_예외가_발생한다(int tryNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new TryNumber(tryNumber))
                .withMessage("시도 횟수 범위를 벗어났습니다. 다시 입력해주세요. 입력된 tryNumber : " + tryNumber);
    }
    
    @Test
    void tryNumber가_1_감소한다() {
        // given
        TryNumber tryNumber = new TryNumber(3);
        
        // when
        TryNumber decreasedTryNumber = tryNumber.decrease();
        
        // then
        assertAll(
                () -> assertThat(decreasedTryNumber.getNumber()).isEqualTo(2),
                () -> assertThat(decreasedTryNumber.getOriginalNumber()).isEqualTo(3)
        );
    }
    
    @Test
    void tryNumber가_0이_되면_isFinished는_true를_반환한다() {
        // given
        TryNumber tryNumber = new TryNumber(1);
        
        // when, then
        assertAll(
                () -> assertThat(tryNumber.isFinished()).isFalse(),
                () -> assertThat(tryNumber.decrease().isFinished()).isTrue()
        );
    }
}
