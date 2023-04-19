package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.exception.invalidinput.CarNameLengthException;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class CarTest {
    Car car1;

    @BeforeEach
    void setUp() {
        car1 = new Car("car1");
    }

    @Nested
    @DisplayName("0~9 의 전진조건을 받아 4이상이면 전진 하고, 3이하이면 멈춘다.")
    class Move {
        @Test
        @DisplayName("전진조건이 4이상으로 전진한다.")
        void case1() {
            car1.move(4);

            assertThat(car1.getPosition()).isEqualTo(1);
        }

        @Test
        @DisplayName("전진조건이 3이하로 멈춘다.")
        void case2() {
            car1.move(3);

            assertThat(car1.getPosition()).isEqualTo(0);
        }
    }


    @Nested
    @DisplayName("자동차 이름이 길이가 1이상 5이하가 아니면 예외를 던진다")
    class ValidateCarName {
        @Test
        @DisplayName("길이가 6이상이면 예외를 던진다.")
        void case1() {
            assertThatThrownBy(() -> new Car("666666")).isInstanceOf(CarNameLengthException.class);
        }

        @Test
        @DisplayName("길이가 5이하면 예외를 던지지 않는다.")
        void case2() {
            assertThatCode(() -> new Car("55555")).doesNotThrowAnyException();
            assertThatNoException().isThrownBy(() -> new Car("55555"));
        }
    }

}
