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
class JdbcWinnerDaoTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcGameDao jdbcGameDao;
    JdbcWinnerDao jdbcWinnerDao;

    @BeforeEach
    void setUp() {
        jdbcGameDao = new JdbcGameDao(dataSource);
        jdbcWinnerDao = new JdbcWinnerDao(jdbcTemplate);
    }

    @Test
    @DisplayName("승자를 저장한다")
    void insertWinner() {
        long gameId = jdbcGameDao.saveGame(1);
        jdbcWinnerDao.insertWinner("폴로, 이리내", gameId);

        String sql = "SELECT count(*) FROM winner WHERE g_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(count).isEqualTo(2);
    }
}
