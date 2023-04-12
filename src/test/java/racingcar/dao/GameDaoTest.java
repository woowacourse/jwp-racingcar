package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameDaoTest {
    @Autowired
    private GameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeEach
    void setUp(){

        jdbcTemplate.execute("DROP TABLE game IF EXISTS");
        jdbcTemplate.execute("create table game\n" +
                "(\n" +
                "    game_number Integer auto_increment primary key,\n" +
                "    created_at  DATETIME default current_timestamp,\n" +
                "    trial_count Integer\n" +
                ");");
    }

    @Test
    void saveGameTest(){
        int trialCount = 10;
        assertThat(gameDao.saveGame(trialCount)).isEqualTo(1);
        assertThat(gameDao.saveGame(trialCount)).isEqualTo(2);
    }
}
