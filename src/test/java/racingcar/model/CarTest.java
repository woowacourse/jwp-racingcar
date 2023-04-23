package racingcar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarTest {

    private static Car car;

    @BeforeEach
    void setUp() {
        car = new Car("io", 0);
    }

    @Test
    @DisplayName("입력된 숫자에 따라 이동 가능 여부를 반환한다")
    void canMovingTest() {
        assertThat(car.canMoving(1)).isFalse();
        assertThat(car.canMoving(3)).isFalse();
        assertThat(car.canMoving(4)).isTrue();
        assertThat(car.canMoving(9)).isTrue();
    }

    @Test
    @DisplayName("이동가능할 시 한 칸 이동한다.")
    void moveTest() {
        car.move(true);

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("차의 위치를 비교하여 더 멀리 이동한 차를 반환한다")
    void getLargerCarTest() {
        final Car compareCar = new Car("oi", 5);

        assertThat(car.getLargerCar(compareCar)).isEqualTo(compareCar);
    }

    @Test
    @DisplayName("차의 위치를 비교하여 동일한 위치에 있는지 여부를 반환한다")
    void isSamePositionCarTest() {
        final Car compareCar1 = new Car("oi", 0);
        final Car compareCar2 = new Car("oio", 1);

        assertThat(car.isSamePositionCar(compareCar1)).isTrue();
        assertThat(car.isSamePositionCar(compareCar2)).isFalse();
    }
}
