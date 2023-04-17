package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.dao.game.JdbcGameDao;

@JdbcTest
class JdbcGameDaoTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    JdbcGameDao jdbcGameDao;

    @BeforeEach
    void setUp() {
        jdbcGameDao = new JdbcGameDao(dataSource, jdbcTemplate);
    }

    @Test
    @DisplayName("game table에 trialCount를 저장한다.")
    void saveGame() {
        long gameId = jdbcGameDao.saveGame(10);
        String sql = "SELECT trialCount FROM game WHERE id = ?";

        Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(trialCount).isEqualTo(10);
    }

    @Test
    @DisplayName("저장된 게임의 모든 id를 가져온다.")
    void getGameIds() {
//        long gameId1 = jdbcGameDao.saveGame(10);
//        long gameId2 = jdbcGameDao.saveGame(10);
//        long gameId3 = jdbcGameDao.saveGame(10);
//        long gameId4 = jdbcGameDao.saveGame(10);
//        long gameId5 = jdbcGameDao.saveGame(10);
//        long gameId6 = jdbcGameDao.saveGame(10);
//
//        List<Long> gameIds = jdbcGameDao.getGameIds();
//
//        assertThat(gameIds).containsExactlyInAnyOrder(gameId1, gameId2, gameId3, gameId4, gameId5, gameId6);
    }
}
