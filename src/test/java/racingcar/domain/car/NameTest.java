package racingcar.domain.car;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.car.Name;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NameTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "aaaaa", "아벨", "아벨리오스"})
    void 길이가_1이상_5이하인_정상적인_이름은_예외가_발생하지_않는다(String name) {
        assertThatNoException()
                .isThrownBy(() -> new Name(name));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaa", "아아벨리오스"})
    void 길이가_1이상_5이하가_아닌_이름은_예외가_발생한다(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("이름의 길이를 벗어났습니다. 다시 입력해주세요.");
    }
}
