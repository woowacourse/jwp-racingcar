package racingcar.dao;

import java.time.LocalDateTime;
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

    public Long insert(int trialCount, LocalDateTime playTime) {
        String sql = "INSERT INTO racing_history (trial_count, play_time) VALUES (:trialCount, :playTime)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(Map.of("trialCount", trialCount, "playTime", playTime)),
                keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

}
