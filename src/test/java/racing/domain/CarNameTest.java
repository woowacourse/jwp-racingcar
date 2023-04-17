package racing.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarNameTest {

    @ParameterizedTest(name = "자동차 이름은 1 ~ 5자 사이이다.")
    @ValueSource(strings = {"123456", "대용량자동차이름"})
    void validateCarNameLengthTest(String input) {
        assertThatThrownBy(() -> new CarName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "자동차 이름은 공백일 수 없다.")
    @ValueSource(strings = {"", "          "})
    void validateCarNameNonBlankTest(String input) {
        assertThatThrownBy(() -> new CarName(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
