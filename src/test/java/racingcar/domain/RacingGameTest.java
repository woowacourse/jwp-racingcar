package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.AlwaysMoveStrategy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RacingGameTest {
    private final MoveStrategy moveStrategy = new AlwaysMoveStrategy();

    private final Car boxster = new Car("박스터");
    private final Car sonata = new Car("소나타");
    private final Car benz = new Car("벤츠");

    private final Cars dummy = new Cars(List.of(
            boxster,
            sonata,
            benz
    ));

    @DisplayName("isEnd 메소드는 게임 종료 여부를 반환한다")
    @ParameterizedTest(name = "시도 횟수가 {0}일 때 {1}번 시도하면 {2}")
    @CsvSource(value = {"3:1:false", "3:2:false", "3:3:true"}, delimiter = ':')
    void isEndTest(int count, int tryCount, boolean result) {
        RacingGame game = new RacingGame(moveStrategy, count, dummy);

        for (int i = 0; i < tryCount; i++) {
            game.playOneRound();
        }

        assertThat(game.isEnd()).isEqualTo(result);
    }

    @Test
    @DisplayName("시도 횟수가 100회 초과하면 예외가 발생한다")
    void tryCountEx() {
        int tryCount = 101;

        assertThatThrownBy(() -> new RacingGame(moveStrategy, tryCount, dummy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 100회 이하만 가능합니다 현재 : " + tryCount + "회");
    }
}
