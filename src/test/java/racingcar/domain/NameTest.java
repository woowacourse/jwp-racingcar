package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @DisplayName("자동차 이름의 길이가 0 이하거나 5보다 클 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} {index} ==> name : ''{0}''")
    @ValueSource(strings = {"", "abcdef", "pobiiiiii", "또링또링또링"})
    void Should_ThrowException_When_NameMoreThan6(String name) {
        assertThatThrownBy(() -> Car.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름의 길이는 1부터 5 사이여야 합니다.");
    }

    @DisplayName("자동차 이름의 길이가 1 이상 5 이하인 경우 성공한다.")
    @ParameterizedTest(name = "{displayName} {index} ==> name : ''{0}''")
    @ValueSource(strings = {"a", "abc", "abcde", "토리", "또링"})
    void Should_Success_When_NameLessThan5(String name) {
        assertDoesNotThrow(() -> Car.from(name));
    }
}
