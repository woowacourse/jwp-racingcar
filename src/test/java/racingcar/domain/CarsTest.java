package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.util.RandomFailPowerMaker;
import racingcar.util.RandomSuccessPowerMaker;

class CarsTest {

    private final RandomSuccessPowerMaker randomSuccessPowerMaker = new RandomSuccessPowerMaker();
    private final RandomFailPowerMaker randomFailPowerMaker = new RandomFailPowerMaker();

    @Test
    @DisplayName("validate() : 자동차 명이 중복이면 에러가 터진다.")
    void test_validate() {
        // given
        List<String> carNames = List.of("jay", "jay");

        // when & then
        assertThatThrownBy(() -> Cars.from(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 중복되지 않아야합니다.");
    }

    @Test
    @DisplayName("moveAll() : 자동차가 모두 움직인다.")
    void test_moveAll_success() {
        // given
        int defaultDistance = 0;
        int expectedDistanceAfterMoveSuccess = defaultDistance + 1;

        Cars cars = Cars.from(List.of("pobi", "crong"));

        // when
        cars.moveAll(randomSuccessPowerMaker);

        // then
        assertAll(
                () -> assertThat(cars.getCars().get(0).getDistance()).isEqualTo(expectedDistanceAfterMoveSuccess),
                () -> assertThat(cars.getCars().get(1).getDistance()).isEqualTo(expectedDistanceAfterMoveSuccess)
        );
    }

    @Test
    @DisplayName("moveAll() : 자동차가 모두 움직이지 않는다.")
    void test_moveAll_fail() {
        // given
        int defaultDistance = 0;
        Cars cars = Cars.from(List.of("pobi", "crong"));

        // when
        cars.moveAll(randomFailPowerMaker);

        // then
        assertAll(
                () -> assertThat(cars.getCars().get(0).getDistance()).isEqualTo(defaultDistance),
                () -> assertThat(cars.getCars().get(1).getDistance()).isEqualTo(defaultDistance)
        );
    }

    @Test
    @DisplayName("test_getWinnerNames() : 가장 많이 움직인 자동차를 반환해준다.")
    void test_getWinnerNames() {
        // given
        Cars cars = Cars.from(List.of("pobi", "crong"));

        // when
        List<String> winnerNames = cars.getWinnerNames();

        // then
        assertAll(
                () -> assertThat(winnerNames.contains("pobi")).isTrue(),
                () -> assertThat(winnerNames.contains("crong")).isTrue()
        );
    }
}
