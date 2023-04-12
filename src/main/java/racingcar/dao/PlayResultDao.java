package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;

    public PlayResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String winners, int trialCount) {
        String sql = "insert into PLAY_RESULT (winners, trial_count) values (?, ?)";
        jdbcTemplate.update(sql, winners, trialCount);
    }
}
