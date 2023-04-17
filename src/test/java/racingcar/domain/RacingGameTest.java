package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.entry;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RacingGameTest {
    @DisplayName("생성한다.")
    @Test
    void create() {
        // given
        final Cars cars = new Cars("헤나,찰리");
        final MovableNumberGenerator movableNumberGenerator = new MovableNumberGenerator();

        // expect
        assertDoesNotThrow(() -> new RacingGame(cars, movableNumberGenerator));
    }

    @DisplayName("레이싱을 trial번 진행하고 게임 결과를 반환한다.")
    @Test
    void raceTimesByTrialThenReturnRacingResult() {
        // given
        final RacingGame racingGame = new RacingGame(new Cars("헤나,찰리"), new MovableNumberGenerator());

        // when
        racingGame.raceTimesBy(10);
        final RacingResult racingResult = racingGame.createRacingResult();
        final LinkedHashMap<Name, Position> history = racingResult.getHistory();

        // then
        assertThat(history).containsExactly(
                entry(new Name("헤나"), new Position(10)),
                entry(new Name("찰리"), new Position(10))
        );
    }
}
