package racingcar.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.Strategy.MovableNumberGenerator;
import racingcar.Strategy.NonMovableNumberGenerator;

class CarTest {

    private Car car;

    @BeforeEach
    void init() {
        car = new Car("밀리");
    }

    @DisplayName("자동차 생성 시 이름이 한 글자에서 다섯 글자가 아닐 때 예외")
    @ParameterizedTest(name = "{displayName} {index} => name : ''{0}''")
    @ValueSource(strings = {"", "wugawuga"})
    void validateName(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 최소 1글자, 최대 5글자까지 가능해요.");
    }

    @Test
    @DisplayName("자동차 한 칸 전진 확인")
    void moveCheck() {
        car.move(1, new MovableNumberGenerator());
        assertThat(car.isMaxPosition(1)).isTrue();
    }

    @Test
    @DisplayName("자동차 정지 확인")
    void stopCheck() {
        car.move(1, new NonMovableNumberGenerator());
        assertThat(car.isMaxPosition(0)).isTrue();
    }

    @Test
    @DisplayName("더 많이 이동한 자동차 위치 확인")
    void findGreaterPosition() {
        car.move(3, new MovableNumberGenerator());
        assertThat(car.findHigherPosition(2)).isEqualTo(3);
    }
}
