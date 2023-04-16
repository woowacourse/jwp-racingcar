package racingcar.database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql(scripts = {"classpath:data.sql"})
class RacingGameDaoTest {
    
    private RacingGameDao gameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    void setUp() {
        this.gameDao = new RacingGameDao(this.jdbcTemplate);
    }
    
    @Test
    @DisplayName("insert - 게임 횟수와 우승자 이름을 받아서 DB에 저장한다.")
    void insertGameTest() {
        final int trialCount = 5;
        final String winners = "io";
        this.gameDao.insert(trialCount, winners);
        this.gameDao.insert(trialCount, winners);
        final String sql = "SELECT count(*) FROM racing_game WHERE trial_count = ? AND winners = ?";
        final int count = this.jdbcTemplate.queryForObject(sql, Integer.class, trialCount, winners);
        Assertions.assertThat(count).isEqualTo(2);
    }
    
    @Test
    @DisplayName("insert - 가장 최근에 저장된 게임의 아이디를 반환한다.")
    void keyTest() {
        final int trialCount = 5;
        final String winners = "io";
        this.gameDao.insert(trialCount, winners);
        final int key = this.gameDao.insert(trialCount, winners);
        Assertions.assertThat(key).isEqualTo(2);
    }
}