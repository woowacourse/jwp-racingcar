package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.utils.TestNumberGenerator;

@DisplayName("RacingGame 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RacingGameTest {

    @Test
    @DisplayName("play 메서드는 자동차 경주 게임을 진행한다.")
    void play_메서드를_호출하면_자동차_경주_게임을_진행한다() {
        // given
        RacingGame racingGame = generateRacingGame(1);

        // when
        racingGame.play();

        // then
        assertThat(racingGame.findWinners()).containsExactly("car1");
    }

    private RacingGame generateRacingGame(final int count) {
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3));
        List<String> names = List.of("car1", "car2");
        return new RacingGame(numberGenerator, names, count);
    }

    @Test
    void getCars_메서드는_현재_경주에_참가하는_자동차들의_이름과_위치를_반환한다() {
        // given
        RacingGame racingGame = generateRacingGame(1);
        racingGame.play();

        // when
        List<Car> result = racingGame.getCars();

        // then
        assertThat(result)
                .extracting(Car::getPosition)
                .containsExactly(1, 0);
    }

    @Test
    void findWinners_메서드는_우승자의_이름목록을_반환한다() {
        // given
        RacingGame racingGame = generateRacingGame(1);
        racingGame.play();

        // when
        List<String> result = racingGame.findWinners();

        // then
        assertThat(result).containsExactly("car1");
    }
}
