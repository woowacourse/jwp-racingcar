package racingcar.dao.raceresult;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;

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

    public String findWinnersByPlayResultId(final int playResultId) {
        String sql = "SELECT winners FROM race_result WHERE id=?";

        return jdbcTemplate.queryForObject(sql, String.class, playResultId);
    }

    public List<Integer> findAllPlayResultId() {
        String sql = "SELECT id FROM race_result";

        return jdbcTemplate.query(sql, playResultIdsRowMapper());
    }

    private RowMapper<Integer> playResultIdsRowMapper() {
        return (rs, rowNum) -> rs.getInt("id");
    }
}
