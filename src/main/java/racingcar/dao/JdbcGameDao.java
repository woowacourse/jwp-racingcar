package racingcar.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.service.RacingResult;

@Repository
public class JdbcGameDao implements GameDao {
    private final SimpleJdbcInsert insertActor;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<RacingResult> actorRowMapper = (resultSet, rowNum) -> new RacingResult(
        resultSet.getInt("id"),
        resultSet.getString("winners"),
        resultSet.getInt("trial_count")
    );

    public JdbcGameDao(final DataSource dataSource, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
            .withTableName("PLAY_RESULT")
            .usingGeneratedKeyColumns("id");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public RacingResult insertRacingResult(final RacingResult racingResult) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(racingResult);

        Number newId = insertActor.executeAndReturnKey(parameterSource);
        racingResult.setId(newId.intValue());
        return racingResult;
    }

    @Override
    public List<RacingResult> selectAllResults() {
        String sql = "select id, trial_count, winners from play_result order by id desc";
        return namedParameterJdbcTemplate.query(sql, actorRowMapper);
    }
}
