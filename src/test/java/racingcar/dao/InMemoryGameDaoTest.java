package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InMemoryGameDaoTest {

    @Autowired
    private GameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE CAR_RESULT");
        jdbcTemplate.execute("DROP TABLE PLAY_RESULT");
        jdbcTemplate.execute("CREATE TABLE PLAY_RESULT (" +
                "    id          INT         NOT NULL AUTO_INCREMENT," +
                "    trial_count INT         NOT NULL," +
                "    winners     VARCHAR(50) NOT NULL," +
                "    created_at  DATETIME    NOT NULL default current_timestamp," +
                "    PRIMARY KEY (id)" +
                ");");
    }

    @Test
    void save() {
        String winner = "ocean";
        int trialCount = 5;
        int gameId = gameDao.save(trialCount,winner);

        assertThat(gameId).isEqualTo(1);
    }
}
