package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CarNameTest {

    @DisplayName("비어있는 이름으로 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void shouldThrowExceptionWhenCreateOfNullOrEmptyOrNull(String inputCarName) {
        assertThatThrownBy(() -> new CarName(inputCarName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름이 비어있습니다. 비어있지 않은 이름을 입력해주세요.");
    }

    @DisplayName("5자 초과의 이름으로 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abcedf", "가나다라마바"})
    void shouldThrowExceptionWhenCreateOfGreaterThan5(String inputCarName) {
        assertThatThrownBy(() -> new CarName(inputCarName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름은 5자 이하여야 합니다.");
    }

}
