package racingcar.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RacingHistoryDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public RacingHistoryDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(int trialCount, LocalDateTime playTime) {
        String sql = "INSERT INTO racing_history (trial_count, play_time) VALUES (:trialCount, :playTime)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(Map.of("trialCount", trialCount, "playTime", playTime)),
                keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

    public List<RacingHistory> findAll() {
        String sql = "SELECT id, trial_count, play_time FROM racing_history";
        return jdbcTemplate.query(sql, ((rs, rowNum) -> new RacingHistory(
                rs.getLong("id"),
                rs.getInt("trial_count"),
                rs.getObject("play_time", LocalDateTime.class)
        )));
    }

}
