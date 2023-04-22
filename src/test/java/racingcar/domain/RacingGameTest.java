package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.strategy.RacingNumberGenerator;

import java.util.List;

class RacingGameTest {

    @Test
    @DisplayName("findWinnerNames 메서드 사용 시 우승자 이름들을 리턴한다.")
    void findWinnerNamesTest() {
        // given
        List<String> carNames = List.of("준팍", "무민", "빙봉", "검프");
        final RacingGame racingGame = RacingGame.of(carNames, 1);
        final RacingNumberGenerator numberGenerator = new TestNumberGenerator(List.of(0, 3, 6, 9));

        // when
        racingGame.playGame(numberGenerator);
        final String expect = String.join(",", carNames.get(2), carNames.get(3));

        // then
        Assertions.assertThat(racingGame.findWinnerNames()).isEqualTo(expect);
    }

}
