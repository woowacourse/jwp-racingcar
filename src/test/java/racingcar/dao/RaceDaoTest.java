package racingcar.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.GameInputDto;

@JdbcTest
@Transactional
class RaceDaoTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private RaceDao raceDao;

    @BeforeEach
    void setUp() {
        JdbcTemplate jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        jdbcTemplate.execute("DROP TABLE RACE CASCADE");
        jdbcTemplate.execute("CREATE TABLE RACE (id INT  NOT NULL AUTO_INCREMENT,"
            + "play_count  INT NOT NULL,"
            + "created_at  DATETIME  NOT NULL default current_timestamp,"
            + "PRIMARY KEY (id))");
        raceDao = new RaceDao(namedParameterJdbcTemplate);
    }

    @Test
    void insert() {
        int raceId = raceDao.insert(new GameInputDto("아벨,스플릿", "12"));
        assertThat(raceId).isOne();
    }
}