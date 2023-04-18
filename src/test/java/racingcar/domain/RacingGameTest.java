package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.config.FixedMovingStrategy;
import racingcar.strategy.MovingStrategy;

class RacingGameTest {

    MovingStrategy movingStrategy = new FixedMovingStrategy();
    RacingGame racingGame;

    @BeforeEach
    void setUp() {
        Cars cars = new Cars(
            List.of(new Car("joy"),
                new Car("pobi"))
        );
        racingGame = new RacingGame(1, cars, movingStrategy);
    }

    @DisplayName("차들의 위치 값을 비교해 최종 우승자들을 구한다.")
    @Test
    void getWinnersTest() {
        List<String> actual = racingGame.winners().stream()
                .map(Car::name)
                .collect(Collectors.toList());

        assertThat(actual).containsExactly("joy", "pobi");
    }
}
