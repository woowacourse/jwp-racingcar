package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class RaceResultDao {

    private final JdbcTemplate jdbcTemplate;

    public RaceResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(final RaceResultRegisterRequest raceResultRegisterRequest) {

        final SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("RACE_RESULT").usingGeneratedKeyColumns("id");

        final SqlParameterSource params = new MapSqlParameterSource()
                .addValue("trial_count", raceResultRegisterRequest.getTrialCount())
                .addValue("winners", raceResultRegisterRequest.getWinners())
                .addValue("created_at", LocalDateTime.now());

        return jdbcInsert.executeAndReturnKey(params).intValue();
    }
}
