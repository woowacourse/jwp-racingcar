package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.RaceResultEntity;

import java.util.List;

@Repository
public class RaceResultDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<RaceResultEntity> rowMapper = (rs, rowNum) ->
            new RaceResultEntity(
                    rs.getLong("id"),
                    rs.getInt("trial_count"),
                    rs.getTimestamp("created_at").toLocalDateTime()
            );

    public RaceResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert =
                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("RACE_RESULT")
                        .usingGeneratedKeyColumns("id");
    }

    public Long save(final RaceResultEntity raceResultEntity) {
        return simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(raceResultEntity))
                               .longValue();
    }

    public List<RaceResultEntity> findAllRaceResult() {
        final String sql = "select * from RACE_RESULT";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
