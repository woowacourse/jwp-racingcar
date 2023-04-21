package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.GameEntity;
import racingcar.domain.RacingGame;
import racingcar.repositoryImpl.RacingGameMapper;

@SuppressWarnings("NonAsciiCharacters")
@JdbcTest
class GamesDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void 저장시_게임_id_가_반환된다() {
        final InsertGameDao insertGameDao = new InsertGameDao(jdbcTemplate);
        final GameEntity gameEntity = RacingGameMapper.toGameEntity(new RacingGame(List.of("브리"), 5));

        final GameEntity result = insertGameDao.insert(gameEntity);

        assertThat(result.getGameId().getValue()).isPositive();
    }

    @Nested
    class SearchGamesTest {

        @BeforeEach
        void setUp() {
            final InsertGameDao insertGameDao = new InsertGameDao(jdbcTemplate);
            final GameEntity gameEntity = RacingGameMapper.toGameEntity(new RacingGame(List.of("브리"), 5));
            insertGameDao.insert(gameEntity);
        }

        @Test
        void 게임_목록을_조회할_수_있다() {
            final GamesDao gamesDao = new GamesDao(jdbcTemplate);
            final List<GameEntity> result = gamesDao.findAll();

            assertThat(result).hasSize(1);
        }
    }
}
