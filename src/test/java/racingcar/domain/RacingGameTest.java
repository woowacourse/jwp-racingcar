package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.race.RacingGame;
import racingcar.domain.race.WinnerJudge;
import racingcar.domain.race.WinnerJudgeImpl;

class RacingGameTest {
    @Nested
    @DisplayName("이름 중복 검증기능")
    class DuplicatedNameTest {
        @Test
        @DisplayName("이름이 중복으로 입력되었을 때 예외 발생")
        void throwExceptionWhenDuplicateNameExists() {
            Assertions.assertThatThrownBy(
                            () -> new RacingGame(List.of("rosie", "hong", "rosie"), new WinnerJudgeImpl()))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("우승자를 판별 기능은")
    class GetWinnersTest {
        private RacingGame race;
        private WinnerJudge mockWinnerJudge;


        @Test
        @DisplayName("우승자이면 true를 반환한다.")
        void shouldContainWinners() {
            // given
            Car winner = new Car("rosie");
            mockWinnerJudge = cars -> List.of(winner);
            race = new RacingGame(List.of("rosie", "hong"), mockWinnerJudge);

            // when

            //then
            assertThat(race.isWinner(winner)).isTrue();
        }

        @Test
        @DisplayName("우승자가 아니면 false를 반환한다.")
        void shouldNotContainNonWinners() {
            // given
            Car winner = new Car("rosie");
            mockWinnerJudge = cars -> List.of(winner);
            race = new RacingGame(List.of("rosie", "hong"), mockWinnerJudge);

            // when
            //then
            assertThat(race.isWinner(new Car("hong"))).isFalse();
        }
    }

    @DisplayName("게임을 실행할 수 있다.")
    @Test
    void testGameProgress() {
        //given
        int trialCount = 10;
        RacingGame race = new RacingGame(List.of("바론", "론이", "로니", "로지"), new WinnerJudgeImpl());
        //when
        race.progress(trialCount);
        //then
        boolean isAllInTrialCount = race.getRacingCars().stream()
                .allMatch(car -> 0 <= car.getPosition() && car.getPosition() <= trialCount);

        assertThat(isAllInTrialCount).isTrue();
    }
}
