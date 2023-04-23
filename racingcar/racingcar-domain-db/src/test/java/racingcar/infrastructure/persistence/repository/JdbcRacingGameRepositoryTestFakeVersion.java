package racingcar.infrastructure.persistence.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingGameRepository;
import racingcar.infrastructure.persistence.dao.CarDao;
import racingcar.infrastructure.persistence.dao.RacingGameDao;
import racingcar.infrastructure.persistence.dao.WinnerDao;
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("JdbcRacingGameRepository 는")
class JdbcRacingGameRepositoryTestFakeVersion {

    private final RacingGameRepository racingGameRepository =
            new JdbcRacingGameRepository(
                    new FaceRacingGameDao(),
                    new FaceCarDao(),
                    new FakeWinnerDao());

    @Test
    void RacingGame_을_저장한다() {
        // given
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
        IntStream.range(0, 10).forEach(it -> racingGameRepository.save(makeGame()));

        // when
        final List<RacingGame> games = racingGameRepository.findAll();

        // then
        assertThat(games.size()).isEqualTo(10);
    }

    private static class FaceRacingGameDao extends RacingGameDao {

        public FaceRacingGameDao() {
            super(new JdbcTemplate());
        }

        private final Map<Long, RacingGameEntity> store = new HashMap<>();

        @Override
        public Long save(final RacingGameEntity racingGameEntity) {
            final Integer id = store.values().size() + 1;
            ReflectionTestUtils.setField(racingGameEntity, "id", id.longValue());
            store.put(id.longValue(), racingGameEntity);
            return id.longValue();
        }

        @Override
        public Optional<RacingGameEntity> findById(final Long id) {
            return Optional.ofNullable(store.getOrDefault(id, null));
        }

        @Override
        public List<RacingGameEntity> findAll() {
            return new ArrayList<>(store.values());
        }
    }

    private static class FaceCarDao extends CarDao {

        public FaceCarDao() {
            super(new JdbcTemplate());
        }

        private final Map<Long, CarEntity> store = new HashMap<>();

        @Override
        public Long save(final CarEntity carEntity) {
            final Integer id = store.values().size() + 1;
            store.put(id.longValue(), carEntity);
            return id.longValue();
        }

        @Override
        public List<CarEntity> findByGameId(final Long gameId) {
            return store.values().stream()
                    .filter(it -> gameId.equals(it.getGameId()))
                    .collect(Collectors.toList());
        }
    }

    private static class FakeWinnerDao extends WinnerDao {

        public FakeWinnerDao() {
            super(new JdbcTemplate());
        }

        private final Map<Long, WinnerEntity> store = new HashMap<>();

        @Override
        public Long save(final WinnerEntity winnerEntity) {
            final Integer id = store.values().size() + 1;
            store.put(id.longValue(), winnerEntity);
            return id.longValue();
        }

        @Override
        public List<WinnerEntity> findByGameId(final Long gameId) {
            return store.values().stream()
                    .filter(it -> gameId.equals(it.getGameId()))
                    .collect(Collectors.toList());
        }
    }
}
