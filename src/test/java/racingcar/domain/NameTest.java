package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NameTest {
    @DisplayName("Name 길이가 1 ~ 5이하일 경우 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "123", "1234", "12345"})
    void create(final String nameValue) {
        assertDoesNotThrow(() -> new Name(nameValue));
    }

    @DisplayName("Name 길이가 1 ~ 5이하가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "123456", "1234567", "12345678"})
    void throwExceptionWhenNameLengthIsNotBetween1And5(final String nameValue) {
        assertThatThrownBy(() -> new Name(nameValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Name은 동등성 검사가 가능하다.")
    @Test
    void equals() {
        // given
        final Name name = new Name("헤나");

        // expect
        assertThat(name).isEqualTo(new Name("헤나"));
    }
}
