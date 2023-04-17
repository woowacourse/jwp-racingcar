package racingcar.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.domain.NumberGenerator;
import racingcar.dto.GameRequest;
import racingcar.dto.GameResponse;
import racingcar.utils.TestNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RacingGameServiceTest {

    @Test
    void 자동차_경주를_진행한다() {
        // given
        final NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 3));
        final RacingGameService racingGameService = new RacingGameService(
                numberGenerator,
                (game) -> 1,
                (cars) -> {
                }
        );
        final GameRequest gameRequest = new GameRequest("브리,비버,허브", 1);

        // when
        final GameResponse gameResponse = racingGameService.play(gameRequest);

        // then
        assertAll(
                () -> assertThat(gameResponse.getWinners()).containsExactly("브리"),
                () -> assertThat(gameResponse.getRacingCars()).hasSize(3)
        );

    }

}
