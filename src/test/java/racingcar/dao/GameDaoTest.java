package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.Game;

@JdbcTest
class GameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameJdbcDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameJdbcDao(jdbcTemplate);
    }

    @AfterEach
    void clear() {
        String sql = "delete from GAME";
        jdbcTemplate.update(sql);
    }

    @Test
    void 게임을_저장한다() {
        Game game = Game.from(10);

        Long id = gameDao.insert(game);

        assertThat(id).isPositive();
    }

    @Test
    void 저장된_게임_정보를_불러온다() {
        Game game1 = Game.from(5);
        Game game2 = Game.from(10);
        gameDao.insert(game1);
        gameDao.insert(game2);

        List<Game> games = gameDao.findAll();

        assertAll(
                () -> assertThat(games).hasSize(2),
                () -> assertThat(games).extracting("trialCount")
                        .containsExactlyInAnyOrder(5, 10)
        );
    }
}
