package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarTest {

    private Car testCar;

    @BeforeEach
    void init() {
        testCar = Car.create("pobi");
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9})
    @DisplayName("랜덤값이 4 이상이면 자동차가 전진한다.")
    void givenFourMoreNumber_thenCarMove(final int randomNumber) {
        // given
        final CarPosition initPosition = testCar.getCarPosition();

        // when
        final Car result = testCar.move(randomNumber);

        // then
        assertThat(result.getCarPosition()).isEqualTo(initPosition.addPosition());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    @DisplayName("랜덤값이 3 이하면 자동차가 정지한다.")
    void givenThreeLessNumber_thenCarStop(final int randomNumber) {
        // given
        final CarPosition initPosition = testCar.getCarPosition();

        // when
        testCar.move(randomNumber);

        // then
        assertThat(testCar.getCarPosition()).isEqualTo(initPosition);
    }

    @Test
    @DisplayName("입력으로 들어온 위치와 현재 자동차의 위치가 일치하면 true를 리턴한다.")
    void givenSamePosition_thenReturnTrue() {
        // given
        Car diffCar = Car.create("test");

        // when
        boolean isSamePosition = testCar.isSamePosition(diffCar);

        // then
        assertThat(isSamePosition)
                .isTrue();
    }

    @Test
    @DisplayName("입력으로 들어온 위치와 현재 자동차의 위치가 일치하지 않는다면 false를 리턴한다.")
    void givenSamePosition_thenReturnFalse() {
        // given
        final Car diffCar = Car.create("test");
        final Car movedCar = diffCar.move(9);

        // when
        boolean isSamePosition = movedCar.isSamePosition(diffCar);

        // then
        assertThat(isSamePosition)
                .isFalse();
    }
}
