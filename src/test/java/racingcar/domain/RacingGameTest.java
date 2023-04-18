package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.util.IdentityNumberGenerator;
import racingcar.util.NumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RacingGameTest {

    @DisplayName("총 3번 횟수 중에서 a는 1번, b는 2번, c는 3번 전진해서 c가 우승한다")
    @Test
    void moveCars_winnerIsC() {
        Cars cars = Cars.of(List.of("a", "b", "c"));
        RacingGame racingGame = new RacingGame(cars, 3);
        NumberGenerator numberGenerator = new IdentityNumberGenerator(List.of(4, 4, 4, 3, 4, 4, 3, 3, 4));

        racingGame.moveCars(numberGenerator);

        assertThat(racingGame.getWinners()).isEqualTo("c");
    }

    @DisplayName("총 3번 횟수 중에서 a는 1번, b는 2번, c는 0번 전진해서 b가 우승한다")
    @Test
    void moveCars_winnerIsB() {
        Cars cars = Cars.of(List.of("a", "b", "c"));
        RacingGame racingGame = new RacingGame(cars, 3);
        NumberGenerator numberGenerator = new IdentityNumberGenerator(List.of(4, 4, 3, 3, 4, 3, 3, 3, 3));

        racingGame.moveCars(numberGenerator);

        assertThat(racingGame.getWinners()).isEqualTo("b");
    }
}
