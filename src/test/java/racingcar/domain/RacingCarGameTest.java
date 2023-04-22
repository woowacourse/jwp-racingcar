package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingCarGameTest {

    private static final String CAR_A_NAME = "carA";
    private static final String CAR_B_NAME = "carB";
    private static final String CAR_C_NAME = "carC";

    @Test
    void 우승한_사용자의_정보를_반환한다() {
        final Car carA = Car.of(CAR_A_NAME, 3);
        final Car carB = Car.of(CAR_B_NAME, 0);
        final Car carC = Car.of(CAR_C_NAME, 3);
        final Cars cars = new Cars(List.of(carA, carB, carC));
        final AttemptNumber attemptNumber = new AttemptNumber(1);
        final TestNumberGenerator testNumberGenerator = new TestNumberGenerator(List.of(0, 0, 0));

        final RacingCarGame racingCarGame = new RacingCarGame(cars, attemptNumber, testNumberGenerator);
        racingCarGame.play();
        final List<String> winners = racingCarGame.getResult().getWinners();

        assertThat(winners).containsExactly(CAR_A_NAME, CAR_C_NAME);
    }

    static class TestNumberGenerator implements NumberGenerator {

        private final List<Integer> testNumberList;
        private int index = 0;

        public TestNumberGenerator(final List<Integer> testNumberList) {
            this.testNumberList = testNumberList;
        }

        @Override
        public int generate() {
            return testNumberList.get(index++);
        }
    }
}
