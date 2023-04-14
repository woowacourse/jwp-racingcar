package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class GameLogDAOTest {

    @Autowired
    private GameLogDAO gameLogDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        gameLogDao = new GameLogDAO(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE game_log IF EXISTS");
        jdbcTemplate.execute("create table game_log\n" +
                "(\n" +
                "    game_number Integer,\n" +
                "    player_name       varchar(20),\n" +
                "    result_position   integer\n" +
                ")");
    }

    @Test
    void insert() {
        assertThatNoException().isThrownBy(() -> gameLogDao.insert(5, "달리", 10));

    }
}
