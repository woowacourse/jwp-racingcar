package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class JdbcGameDaoTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    JdbcGameDao jdbcGameDao;

    @BeforeEach
    void setUp() {
        jdbcGameDao = new JdbcGameDao(dataSource);
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
