package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class ResultDao {

    private final JdbcTemplate jdbcTemplate;

    public ResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long insert(int trialCount, String winners) {
        String sql = "INSERT INTO RESULTS (TRIAL_COUNT, WINNERS) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, trialCount);
            ps.setString(2, winners);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
