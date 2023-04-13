package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class JdbcGameDaoTest {
    JdbcGameDao jdbcGameDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcGameDaoTest(JdbcGameDao jdbcGameDao, JdbcTemplate jdbcTemplate) {
        this.jdbcGameDao = jdbcGameDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    @DisplayName("game table에 trialCount를 저장한다.")
    void saveGame() {
        long gameId = jdbcGameDao.saveGame(10);
        String sql = "SELECT trialCount FROM game WHERE id = ?";

        Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(trialCount).isEqualTo(10);
    }
}
