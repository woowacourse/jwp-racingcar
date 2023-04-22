package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.DatabaseTest;

@DatabaseTest
class GameStatesDaoTest {

    @Autowired
    private GameStatesDao gameStatesDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("게임 추가 테스트")
    void insert_test() {
        final int gameId = gameStatesDao.insert(5, 0);

        final String sql = "select remaining_trial_count from gamestates where id=?";
        final Integer trialCount = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(trialCount).isEqualTo(0);
    }
}
