package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class GameLogDaoTest {

    @Autowired
    private GameLogDao gameLogDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        gameLogDao = new GameLogDao(jdbcTemplate);

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

    @Test
    void load() {
        gameLogDao.insert(1, "달리", 10);
        gameLogDao.insert(1, "달", 9);
        gameLogDao.insert(1, "물빠진떡", 10);

        assertThat(gameLogDao.load(1).size()).isEqualTo(3);
    }
}
