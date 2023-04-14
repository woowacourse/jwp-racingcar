package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarPositionTest {

    private CarPosition carPosition;

    @BeforeEach
    void init() {
        carPosition = CarPosition.create();
    }

    @Test
    @DisplayName("위치 값을 1 증가시킨다.")
    void whenAddPosition_thenPositionPlus() {
        // given
        final CarPosition beforePosition = carPosition;

        // when
        final CarPosition result = carPosition.addPosition();

        // then
        assertThat(result)
            .isEqualTo(beforePosition.addPosition());
    }
}
