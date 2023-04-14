package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CarNameTest {

    @ParameterizedTest
    @DisplayName("정상적인 차 이름이 들어오면 CarName 객체 시 예외가 발생하지 않는다.")
    @ValueSource(strings = {"junpk", "jney", "pobi", "neo"})
    void create_success(final String carName) {
        assertThat(CarName.create(carName))
                .isInstanceOf(CarName.class);

        assertThatCode(() -> CarName.create(carName))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("5글자 초과의 이름이 들어왔을 경우 예외가 발생한다.")
    @ValueSource(strings = {"junpak", "journey", "pobiconan", "neocat"})
    void create_fail_givenFiveOverLength(final String carName) {
        assertThatThrownBy(() -> CarName.create(carName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%d글자를 초과하였습니다.", 5));
    }

    @ParameterizedTest
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    @EmptySource
    void create_fail_givenBlankName(final String carName) {
        assertThatThrownBy(() -> CarName.create(carName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%s은(는) 빈 값이 들어올 수 없습니다.", "이름"));
    }
}
