package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.Game;
import racingcar.entity.Player;

@JdbcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayerJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayerDao playerDao;
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        playerDao = new PlayerJdbcDao(jdbcTemplate);
        gameDao = new GameJdbcDao(jdbcTemplate);
    }

    @Test
    void 입력받은_플레이어를_전부_저장한다() {
        // given
        final int gameId = gameDao.saveAndGetId(new Game(3));
        final List<Player> players = List.of(new Player("car1", 1, true, gameId));

        // when
        playerDao.saveAll(players);

        // then
        final String sql = "select count(*) from player";
        final int count = jdbcTemplate.queryForObject(sql, Integer.class);
        assertThat(count).isEqualTo(1);
    }
}
