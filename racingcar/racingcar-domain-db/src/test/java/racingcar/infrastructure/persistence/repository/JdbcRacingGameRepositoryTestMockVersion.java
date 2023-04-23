package racingcar.infrastructure.persistence.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.infrastructure.persistence.dao.CarDao;
import racingcar.infrastructure.persistence.dao.RacingGameDao;
import racingcar.infrastructure.persistence.dao.WinnerDao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static racingcar.infrastructure.persistence.entity.CarEntityFixture.carEntitiesOfSize;
import static racingcar.infrastructure.persistence.entity.RacingGameEntityFixture.racingGameEntitiesOfSize;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("JdbcRacingGameRepository 는")
class JdbcRacingGameRepositoryTestMockVersion {

    private RacingGameDao gameDao = mock(RacingGameDao.class);
    private CarDao carDao = mock(CarDao.class);
    private WinnerDao winnerDao = mock(WinnerDao.class);

    private RacingGameRepository racingGameRepository = new JdbcRacingGameRepository(gameDao, carDao, winnerDao);

    @Test
    void RacingGame_을_저장한다() {
        // given
        given(gameDao.save(any())).willReturn(1L);
        final RacingGame racingGame = makeGame();

        // when
        final Long id = racingGameRepository.save(racingGame);

        // then
        assertThat(id).isEqualTo(1L);
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
        given(gameDao.findAll())
                .willReturn(racingGameEntitiesOfSize(2));
        given(carDao.findByGameId(1L))
                .willReturn(carEntitiesOfSize(2, 1L));
        given(carDao.findByGameId(2L))
                .willReturn(carEntitiesOfSize(2, 2L));

        // when
        final List<RacingGame> games = racingGameRepository.findAll();

        // then
        assertThat(games.size()).isEqualTo(2);
    }
}
