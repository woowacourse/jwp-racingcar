package racingcar.domain.race;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingCarsTest {

    @DisplayName("중복되는 차 이름은 예외가 발생한다")
    @Test
    void racingCarDuplicateFail() {
        assertThatThrownBy(() -> new RacingCars(List.of("바론", "바론")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1등의 위치를 반환할 수 있다.")
    @Test
    void testCalculateWinPosition() {
        RacingCars racingCars = new RacingCars(List.of("로지", "포지"));
        racingCars.moveCars(List.of(10, 1));
        int winPosition = racingCars.calculateWinnerPosition();
        assertThat(winPosition).isEqualTo(1);
    }

}
