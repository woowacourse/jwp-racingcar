package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void 차량_전진_테스트() {
        //given
        final int DRIVING_DISTANCE = 1;
        Car car = new Car("test");
        //when
        car.drive(4);
        //then
        assertThat(car.getDrivenDistance()).isEqualTo(DRIVING_DISTANCE);
    }

    @Test
    void 차량_정지_테스트() {
        //given
        Car car = new Car("test");
        //when
        car.drive(3);
        //then
        assertThat(car.getDrivenDistance()).isEqualTo(0);
    }
}
