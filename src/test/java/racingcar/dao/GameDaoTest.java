package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class GameDaoTest {
    @Autowired
    private GameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE game_log IF EXISTS");
        jdbcTemplate.execute("DROP TABLE winners IF EXISTS");
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");
        jdbcTemplate.execute("create table game\n" +
                "(\n" +
                "    game_number bigint auto_increment,\n" +
                "    created_at  DATETIME default current_timestamp,\n" +
                "    trial_count Integer\n" +
                ") ;");
    }

    @Test
    void saveGameTest() {
        int trialCount = 10;
        assertThat(gameDao.saveGame(trialCount)).isEqualTo(1);
        assertThat(gameDao.saveGame(trialCount)).isEqualTo(2);
    }

    @Test
    void load(){
        gameDao.saveGame(10);
        gameDao.saveGame(10);
        gameDao.saveGame(10);

        assertThat(gameDao.load().size()).isEqualTo(3);
    }
}
