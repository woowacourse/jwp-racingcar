package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarPositionTest {

    private CarPosition carPosition;

    @BeforeEach
    void init() {
        carPosition = CarPosition.init();
    }

    @Test
    @DisplayName("초기 CarPosition 객체 생성 시 position은 1이어야 한다.")
    void givenNormalPosition_thenSuccess() {
        // given, when
        int position = carPosition.getPosition();

        // then
        assertThat(carPosition)
                .isInstanceOf(CarPosition.class);

        assertThat(position)
                .isEqualTo(1);
    }

    @Test
    @DisplayName("위치 값을 1 증가시킨다.")
    void whenAddPosition_thenPositionPlus() {
        // given
        final int beforePosition = carPosition.getPosition();

        // when
        final CarPosition newPosition = carPosition.addPosition();

        // then
        assertThat(newPosition)
                .isEqualTo(CarPosition.create(beforePosition + 1));
    }
}
