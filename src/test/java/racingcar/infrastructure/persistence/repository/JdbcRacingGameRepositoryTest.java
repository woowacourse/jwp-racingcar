package racingcar.infrastructure.persistence.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
@DisplayName("JdbcRacingGameRepository 는")
class JdbcRacingGameRepositoryTest {

    @Autowired
    private RacingGameRepository racingGameRepository;

    @Test
    void RacingGame_을_저장한다() {
        // given
        final RacingGame racingGame = makeGame();

        // when
        final Long id = racingGameRepository.save(racingGame);

        // then
        assertThat(racingGameRepository.findById(id)).isNotEmpty();
    }

    private RacingGame makeGame() {
        final Cars cars = new Cars(Stream.of("브리", "토미", "브라운")
                .map(Car::new)
                .collect(Collectors.toList()));
        final GameTime gameTime = new GameTime("10");
        return new RacingGame(cars, gameTime);
    }

    @Test
    void 모든_게임을_조회한다() {
        // given
        IntStream.range(0, 10).forEach(it -> racingGameRepository.save(makeGame()));

        // when
        final List<RacingGame> games = racingGameRepository.findAll();

        // then
        // TODO 이정도만 검증해도 될까요?
        assertThat(games.size()).isEqualTo(10);
    }
}
