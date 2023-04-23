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
        assertThatThrownBy(() -> new RacingCars(List.of(new RacingCar("바론"), new RacingCar("바론"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가장 멀리간 자동차는 우승자이다.")
    @Test
    void testIsWinner() {
        RacingCar winner = new RacingCar("하이", 9);
        RacingCars racingCars = new RacingCars(List.of(
                winner,
                new RacingCar("바이", 1),
                new RacingCar("헤이", 2)
        ));
        boolean isWinner = racingCars.isWinner(winner);

        assertThat(isWinner).isTrue();
    }

    @DisplayName("가장 멀리간 자동차가 아닐경우 우승자가 아니다.")
    @Test
    void testIsNotWinner() {
        RacingCar nonWinner = new RacingCar("하이", 1);
        RacingCars racingCars = new RacingCars(List.of(
                nonWinner,
                new RacingCar("바이", 10),
                new RacingCar("헤이", 2)
        ));
        boolean isWinner = racingCars.isWinner(nonWinner);

        assertThat(isWinner).isFalse();
    }

    @DisplayName("빈 리스트가 들어오는 경우")
    @Test
    void testCreateWithEmptyList() {
        assertThatThrownBy(() -> new RacingCars(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
