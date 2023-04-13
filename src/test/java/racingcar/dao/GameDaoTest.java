package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameDaoTest {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE car IF EXISTS");
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE GAME ("
                + "id          INT         NOT NULL AUTO_INCREMENT,"
                + "trial_count  INT         NOT NULL,"
                + "created_at  DATETIME    NOT NULL default current_timestamp,"
                + "PRIMARY KEY (id)"
                + ");"
        );
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void insert() {
        int gameId = gameDao.insertGame(5);
        assertThat(gameId).isEqualTo(1);
    }
}
