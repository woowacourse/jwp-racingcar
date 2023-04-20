package racingcar.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.domain.NumberGenerator;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;
import racingcar.repositiory.RacingGameRepository;
import racingcar.utils.TestNumberGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RacingGameServiceTest {

    @Test
    void 자동차_경주를_진행한다() {
        // given
        RacingGameRepository racingGameRepository = Mockito.mock(RacingGameRepository.class);
        final NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 3));
        final RacingGameService racingGameService = new RacingGameService(
                numberGenerator,
                racingGameRepository
        );
        final GameRequestDto gameRequest = new GameRequestDto(List.of("브리", "비버", "허브"), 1);

        // when
        final GameResponseDto gameResponse = racingGameService.play(gameRequest);

        // then
        assertAll(
                () -> assertThat(gameResponse.getWinners()).containsExactly("브리"),
                () -> assertThat(gameResponse.getRacingCars()).hasSize(3)
        );
    }
}
