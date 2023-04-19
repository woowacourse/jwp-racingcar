package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @ParameterizedTest
    @ValueSource(strings = {"123456", "123456789"})
    void 생성_실패_이름_5글자_초과(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 5글자를 초과할 수 없습니다.");
    }

    @Test
    void 생성_실패_이름_1글자_미만() {
        assertThatThrownBy(() -> new Car(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 1글자 미만일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 10})
    void move_메서드는_power가_4이상이면_전진(int power) {
        Car car = new Car("test");
        int positionBeforeMove = car.getPosition();
        car.move(power);
        assertThat(car.getPosition()).isEqualTo(positionBeforeMove + 1);
    }
}
