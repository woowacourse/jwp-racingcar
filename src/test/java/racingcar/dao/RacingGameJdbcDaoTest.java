package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RacingGameJdbcDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void insert() {
        String winners = "win1,win2";
        int trialCount = 5;
        Long gameId = racingGameDao.save(winners, trialCount);

        String sqlForWinners = "SELECT winners FROM racing_game WHERE id = " + gameId;
        String sqlForTrialCount = "SELECT trial_count FROM racing_game WHERE id = " + gameId;

        Integer savedTrialCount = jdbcTemplate.queryForObject(sqlForTrialCount, Integer.class);
        String savedWinners = jdbcTemplate.queryForObject(sqlForWinners, String.class);

        assertThat(trialCount).isEqualTo(savedTrialCount);
        assertThat(winners).isEqualTo(savedWinners);
    }
}
