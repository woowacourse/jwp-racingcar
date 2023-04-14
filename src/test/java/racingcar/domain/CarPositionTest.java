package racingcar.domain;

import org.assertj.core.api.AssertionsForClassTypes;
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
    void getPosition() {
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
    void addPosition() {
        // given
        final int beforePosition = carPosition.getPosition();

        // when
        final CarPosition newPosition = carPosition.addPosition();

        // then
        assertThat(newPosition)
                .isEqualTo(CarPosition.create(beforePosition + 1));
    }

    @Test
    @DisplayName("입력으로 들어온 위치와 현재 자동차의 위치가 일치하면 true를 리턴한다.")
    void isSamePosition_true() {
        // given
        final CarPosition diffPosition = CarPosition.create(1);

        // when
        boolean isSamePosition = carPosition.isSamePosition(diffPosition);

        // then
        AssertionsForClassTypes.assertThat(isSamePosition)
                .isTrue();
    }

    @Test
    @DisplayName("입력으로 들어온 위치와 현재 자동차의 위치가 일치하지 않는다면 false를 리턴한다.")
    void isSamePosition_false() {
        // given
        final CarPosition diffPosition = CarPosition.create(10);

        // when
        boolean isSamePosition = carPosition.isSamePosition(diffPosition);

        // then
        AssertionsForClassTypes.assertThat(isSamePosition)
                .isFalse();
    }
}
