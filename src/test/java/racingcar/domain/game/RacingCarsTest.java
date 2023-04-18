package racingcar.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.cars.RacingCars;

class RacingCarsTest {

    @DisplayName("중복되는 차 이름은 예외가 발생한다")
    @Test
    void racingCarDuplicateFail() {
        assertThatThrownBy(() -> new RacingCars(List.of(new RacingCar(0, "바론"), new RacingCar(0, "바론"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가장 먼 위치를 반환할 수 있다.")
    @Test
    void testCalculateWinPosition() {
        RacingCars racingCars = new RacingCars(List.of(new RacingCar(0, "로지"), new RacingCar(0, "포지")));
        racingCars.moveCars(List.of(10, 1));
        int maxPosition = racingCars.calculateMaxPosition();
        assertThat(maxPosition).isEqualTo(1);
    }

}
