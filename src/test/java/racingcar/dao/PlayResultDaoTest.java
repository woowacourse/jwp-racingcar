package racingcar.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.PlayResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayResultDaoTest {

    private final PlayResultDao mapper;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlayResultDaoTest(PlayResultDao playResultDao, JdbcTemplate jdbcTemplate){
        this.mapper = playResultDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE CAR_RESULT IF EXISTS");
        jdbcTemplate.execute("DROP TABLE PLAY_RESULT IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS PLAY_RESULT" +
                "(" +
                "    id         INT         NOT NULL AUTO_INCREMENT," +
                "    trial_count INT         NOT NULL," +
                "    winners    VARCHAR(120) NOT NULL," +
                "    created_at DATETIME    NOT NULL," +
                "    PRIMARY KEY (id)" +
                ");");
    }

    @AfterEach
    void deleteTable() {
        jdbcTemplate.execute("DROP TABLE CAR_RESULT IF EXISTS");
        jdbcTemplate.execute("DROP TABLE PLAY_RESULT IF EXISTS");
    }

    @Test
    void key() {
        PlayResult entity = PlayResult.of(10, "aa", Timestamp.valueOf(LocalDateTime.now()));
        Integer id = mapper.save(entity);;
        PlayResult result = mapper.findById(id);
        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
