package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import racingcar.dao.CarDao;
import racingcar.dao.GameDao;
import racingcar.domain.NumberGenerator;
import racingcar.dto.GamePlayRequestDto;
import racingcar.dto.GamePlayResponseDto;
import racingcar.utils.TestNumberGenerator;

@DisplayName("RacingGameService 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class RacingGameServiceTest {

    @Test
    void play_메서드는_자동차_경주를_진행한다() {
        // given
        final GameDao gameDao = Mockito.mock(GameDao.class);
        final CarDao carDao = Mockito.mock(CarDao.class);
        final NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 3));
        final RacingGameService racingGameService = new RacingGameService(numberGenerator, gameDao, carDao);
        final GamePlayRequestDto gameRequest = new GamePlayRequestDto(List.of("브리", "비버", "허브"), 1);

        // when
        final GamePlayResponseDto gameResponse = racingGameService.play(gameRequest);

        // then
        assertAll(
                () -> assertThat(gameResponse.getWinners()).containsExactly("브리"),
                () -> assertThat(gameResponse.getRacingCars()).hasSize(3)
        );
    }
}
