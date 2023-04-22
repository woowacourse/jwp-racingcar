package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class RacingGameTest {
    private final List<String> carNames = List.of("포르쉐", "현대", "기아");

    @Test
    @DisplayName("play 메소드를 호출하면 모든 자동차의 position이 1 증가한다.")
    void play_with_always_move() {
        TryCount tryCount = new TryCount(1);

        RacingGame game = new RacingGame(new AlwaysMoveGenerator(), Cars.fromNameValues(carNames), tryCount);
        game.play();

        List<Car> result = game.getCars();

        List<Integer> positions = result.stream()
                .map(Car::getPosition)
                .collect(Collectors.toList());

        Assertions.assertThat(positions).containsOnly(1);
    }

    @Test
    @DisplayName("play 메소드를 호출하면 모든 자동차의 position이 1 증가하지 않는다.")
    void play_with_always_stop() {
        TryCount tryCount = new TryCount(1);

        RacingGame game = new RacingGame(new NeverMoveGenerator(), Cars.fromNameValues(carNames), tryCount);
        game.play();

        List<Car> result = game.getCars();

        List<Integer> positions = result.stream()
                .map(Car::getPosition)
                .collect(Collectors.toList());

        Assertions.assertThat(positions).containsOnly(0);
    }

    private static class AlwaysMoveGenerator implements NumberGenerator {

        @Override
        public int generate() {
            return 4;
        }
    }

    private static class NeverMoveGenerator implements NumberGenerator {

        @Override
        public int generate() {
            return 3;
        }
    }
}
