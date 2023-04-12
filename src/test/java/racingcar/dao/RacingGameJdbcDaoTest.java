package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RacingGameJdbcDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void insert() {
        int trialCount = 5;
        Long gameId = racingGameDao.save(trialCount);

        String sql = "SELECT trial_count FROM racing_game WHERE id = " + gameId;
        Integer savedTrialCount = jdbcTemplate.queryForObject(sql, Integer.class);

        assertThat(trialCount).isEqualTo(savedTrialCount);
    }
}
