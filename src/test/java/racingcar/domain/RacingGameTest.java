package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.game.RacingGame;
import racingcar.domain.game.RandomNumberGenerator;

class RacingGameTest {
    @Nested
    @DisplayName("이름 중복 검증기능")
    class DuplicatedNameTest {
        @Test
        @DisplayName("이름이 중복으로 입력되었을 때 예외 발생")
        void throwExceptionWhenDuplicateNameExists() {
            Assertions.assertThatThrownBy(
                            () -> RacingGame.create(0, List.of("rosie", "hong", "rosie")))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("우승자를 판별 기능은")
    class GetWinnersTest {
        private RacingGame race;


        @Test
        @DisplayName("우승자이면 true를 반환한다.")
        void shouldContainWinners() {
            // given
            race = RacingGame.create(0, List.of("로지", "홍실"));
            race.play(1, (size) -> List.of(10, 1));
            //when
            //then
            assertThat(race.isWinner(new RacingCar(0, "로지"))).isTrue();
        }

        @Test
        @DisplayName("우승자가 아니면 false를 반환한다.")
        void shouldNotContainNonWinners() {
            // given
            race = RacingGame.create(0, List.of("rosie", "hong"));
            race.play(1, (size) -> List.of(10, 1));
            // when
            //then
            assertThat(race.isWinner(new RacingCar(0, "hong"))).isFalse();
        }
    }

    @DisplayName("게임을 실행할 수 있다.")
    @Test
    void testGameProgress() {
        //given
        int trialCount = 10;
        RacingGame race = RacingGame.create(0, List.of("바론", "론이", "로니", "로지"));
        //when
        race.play(trialCount, new RandomNumberGenerator());
        //then
        boolean isAllInTrialCount = race.getRacingCars().stream()
                .allMatch(car -> 0 <= car.getPosition() && car.getPosition() <= trialCount);

        assertThat(isAllInTrialCount).isTrue();
    }

    @DisplayName("id와 자동차 이름으로 객체를 생성할 수 있다.")
    @Test
    void testCreateInstanceOfIdAndCarNames() {
        long gameHistoryId = 1;
        List<String> carNames = List.of("서브웨이", "로지", "키아라", "연어");
        var racingGame = RacingGame.create(gameHistoryId, carNames);

        assertThat(racingGame)
                .isNotNull()
                .isInstanceOf(RacingGame.class);
    }


}
