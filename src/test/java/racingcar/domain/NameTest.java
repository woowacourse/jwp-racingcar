package racingcar.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.IllegalGameArgumentException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaa"})
    void 이름_길이_1글자이상_5글자_이하가_아니면_예외를_던진다(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalGameArgumentException.class);
    }
}
