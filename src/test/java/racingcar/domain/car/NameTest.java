package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest(name = "공백으로만 이뤄지거나 1글자 미만 5글자 초과의 이름이 들어오면 예외를 던진다. input = {0}")
    @ValueSource(strings = {"      ", "", "length"})
    void nameCreateFail(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1글자 이상 5자 이하의 이름을 입력해주세요.");
    }
}
