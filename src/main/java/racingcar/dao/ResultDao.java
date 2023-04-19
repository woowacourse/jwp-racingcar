package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ResultDao {

    private final JdbcTemplate jdbcTemplate;

    public ResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long insert(int trialCount, String winners) {
        String sql = "insert into results (trial_count, winners) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, trialCount);
            ps.setString(2, winners);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<Long> findAllId() {
        String sql = "select id from results";
        return jdbcTemplate.queryForList(sql, Long.class);
    }
}
