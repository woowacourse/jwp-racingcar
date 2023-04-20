package racingcar.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.application.RacingGameService.GameResult;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("RacingGameService 은")
class RacingGameServiceTest {

    private NumberGenerator numberGenerator = mock(NumberGenerator.class);
    private RacingGameRepository racingGameRepository = mock(RacingGameRepository.class);

    private RacingGameService racingGameService = new RacingGameService(numberGenerator, racingGameRepository);

    private RacingGame makeGame() {
        return new RacingGame(
                new Cars(List.of(
                        new Car("브리", 3),
                        new Car("토미", 2))),
                new GameTime(5)
        );
    }

    @Test
    void 게임을_진행한다() {
        // given
        given(racingGameRepository.save(any()))
                .willReturn(1L);
        given(racingGameRepository.findById(1L))
                .willReturn(Optional.of(makeGame()));

        // when
        final Long id = racingGameService.play(List.of("브리", "토미"), 10);

        // then
        final GameResult gameResult = racingGameService.findResultById(id);
        assertThat(gameResult.getWinners()).containsExactly("브리");
        assertThat(gameResult.getRacingCars().size()).isEqualTo(2);
    }

    @Test
    void 전체_게임을_조회한다() {
        // given
        given(racingGameRepository.findAll())
                .willReturn(List.of(makeGame(), makeGame(), makeGame()));

        // when
        final List<GameResult> results = racingGameService.findAll();

        // then
        assertThat(results.size()).isEqualTo(3);
    }
}
