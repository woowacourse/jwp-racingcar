package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.GameResultEntity;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayName("GameResultDao 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class GameResultJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameResultDao gameResultDao;

    @BeforeEach
    void setup() {
        gameResultDao = new GameResultJdbcDao(jdbcTemplate);
    }

    @Test
    void save_test() {
        // given & when
        final Long gameId = gameResultDao.insert(new GameResultEntity(3));

        // then
        assertThat(gameId).isEqualTo(1L);
    }

    @Test
    void findAll_test() {
        // given
        gameResultDao.insert(new GameResultEntity(3));

        // then
        Assertions.assertThat(gameResultDao.findAll()).hasSize(1);
    }
}
