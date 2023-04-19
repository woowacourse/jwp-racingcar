package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RacingGameTest {

    @DisplayName("자동차 경주 게임을 진행할 수 있다.")
    @Test
    void playRacingGame() {
        RacingCar ethan = TestCarMaker.createMoveRacingCar("에단");
        RacingCar konghana = TestCarMaker.createNotMoveRacingCar("콩하나");
        RacingGame racingGame = new RacingGame(new RacingCars(List.of(ethan, konghana)), new TryCount(10));

        RacingCars racingCars = racingGame.moveCars();
        Assertions.assertThat(racingCars.getWinnerNames()).containsExactly("에단");
    }
}
