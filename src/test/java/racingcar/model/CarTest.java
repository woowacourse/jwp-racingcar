package racingcar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @DisplayName("자동차 이동 테스트 실패")
    @Test
    void isMoveFail() {
        Car car = new TestCar("false", List.of(3));

        assertThat(car.isMove()).isEqualTo(false);
    }

    @DisplayName("자동차 이동 테스트 성공")
    @Test
    void isMoveSuccess() {
        Car car = new TestCar("true", List.of(4));

        assertThat(car.isMove()).isEqualTo(true);
    }

    @DisplayName("자동차 이름 길이 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "가나다라마바,가나", " ", "  "})
    void validate(String input) {
        List<String> carNames = Arrays.asList(input.split(","));
        assertThatThrownBy(() -> carNames.stream().map(Car::new).collect(Collectors.toList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차명은 1 ~ 5 글자로 입력해야합니다.");
    }
}
