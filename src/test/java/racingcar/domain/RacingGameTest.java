package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.race.RacingGame;

class RacingGameTest {
    @Nested
    @DisplayName("이름 중복 검증기능")
    class DuplicatedNameTest {
        @Test
        @DisplayName("이름이 중복으로 입력되었을 때 예외 발생")
        void throwExceptionWhenDuplicateNameExists() {
            Assertions.assertThatThrownBy(
                            () -> new RacingGame(List.of("rosie", "hong", "rosie")))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("게임을 실행할 수 있다.")
    @Test
    void testGameProgress() {
        //given
        int trialCount = 10;
        RacingGame race = new RacingGame(List.of("바론", "론이", "로니", "로지"));
        //when
        race.progress(trialCount);
        //then
        boolean isAllInTrialCount = race.getRacingCars().stream()
                .allMatch(car -> 0 <= car.getPosition() && car.getPosition() <= trialCount);

        assertThat(isAllInTrialCount).isTrue();
    }

    @DisplayName("시도 횟수가 0 이하면 예외가 발생한다.")
    @Test
    void progressFailWhenWrongTrialCount() {
        //given
        int trialCount = -1;
        RacingGame racingGame = new RacingGame(List.of("로지", "브리"));
        //when
        //then
        assertThatThrownBy(() -> racingGame.progress(trialCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
