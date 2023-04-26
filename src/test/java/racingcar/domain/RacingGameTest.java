package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    private final Cars dummy = new Cars(List.of(
            new Car("박스터"),
            new Car("소나타"),
            new Car("벤츠")
    ));

    @Test
    @DisplayName("레이싱 게임을 진행할 때 4이상의 숫자가 주어지면 우승자의 위치가 3이다.")
    void moveTest() {
        RacingGame game = new RacingGame(new AlwaysMoveNumberGenerator(), 3, dummy);

        game.run();

        List<Car> carList = dummy.getCars();
        assertAll(() -> {
            assertThat(carList)
                    .extracting("name").contains("박스터", "소나타", "벤츠");
            assertThat(carList)
                    .extracting("position").containsOnly(3);
        });
    }

    @Test
    @DisplayName("레이싱 게임을 진행할 때 3이하의 숫자가 주어지면 우승자의 위치가 0이다.")
    void notMoveTest() {
        RacingGame game = new RacingGame(new NeverMoveNumberGenerator(), 3, dummy);

        game.run();

        List<Car> carList = dummy.getCars();
        assertAll(() -> {
            assertThat(carList)
                    .extracting("name").contains("박스터", "소나타", "벤츠");
            assertThat(carList)
                    .extracting("position").containsOnly(0);
        });
    }

    @Test
    @DisplayName("시도 횟수가 100회 초과하면 예외가 발생한다")
    void tryCountEx() {
        int tryCount = 101;

        assertThatThrownBy(() -> new RacingGame(tryCount, dummy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 100회 이하만 가능합니다 현재 : " + 101 + "회");
    }

    static class AlwaysMoveNumberGenerator implements NumberGenerator {

        public static final int MOVE_NUMBER = 4;

        @Override
        public int generate() {
            return MOVE_NUMBER;
        }

    }

    static class NeverMoveNumberGenerator implements NumberGenerator {

        public static final int NOT_MOVE_NUMBER = 3;

        @Override
        public int generate() {
            return NOT_MOVE_NUMBER;
        }

    }
}
