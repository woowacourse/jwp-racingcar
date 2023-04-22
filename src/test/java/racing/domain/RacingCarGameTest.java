package racing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racing.domain.fixture.CarFixtureFactory;
import racing.domain.utils.RacingCarNumberGenerator;
import racing.domain.utils.ScheduledNumberGenerator;

class RacingCarGameTest {

    private static final int STOP_NUMBER = 1;
    private static final int MOVE_NUMBER = 9;

    @DisplayName("게임 시도 횟수 만큼 자동차가 전진한다")
    @Test
    void playRoundsTest() {
        int trialCount = 2;
        Car carA = CarFixtureFactory.getCarWithName("carA");
        Car carB = CarFixtureFactory.getCarWithName("carB");
        Cars cars = new Cars(List.of(carA, carB));
        RacingCarGame racingCarGame = new RacingCarGame(cars, new TrialCount(trialCount));
        RacingCarNumberGenerator scheduledNumberGenerator =
                new ScheduledNumberGenerator(STOP_NUMBER, MOVE_NUMBER, STOP_NUMBER, MOVE_NUMBER);

        racingCarGame.playRounds(scheduledNumberGenerator);

        assertThat(carA.getStep()).isEqualTo(0);
        assertThat(carB.getStep()).isEqualTo(2);
    }

    @DisplayName("게임 결과에 따라 승리한 자동차를 반환 한다.")
    @Test
    void winnerCarsTest() {
        int trialCount = 2;
        Car carA = CarFixtureFactory.getCarWithNameAndStep("carA", 1);
        Car carB = CarFixtureFactory.getCarWithNameAndStep("carB", 3);
        Car carC = CarFixtureFactory.getCarWithNameAndStep("carC", 3);
        Cars cars = new Cars(List.of(carA, carB, carC));
        RacingCarGame racingCarGame = new RacingCarGame(cars, new TrialCount(trialCount));

        Cars winnerCars = racingCarGame.winnerCars();

        assertThat(winnerCars.getCars()).contains(carB, carC);
    }
}
