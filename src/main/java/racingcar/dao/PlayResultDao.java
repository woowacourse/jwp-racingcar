package racingcar.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.service.PlayResult;

@Repository
public class PlayResultDao {
    private final SimpleJdbcInsert insertActor;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<PlayResult> actorRowMapper = (resultSet, rowNum) -> new PlayResult(
            resultSet.getInt("id"),
            resultSet.getString("winners"),
            resultSet.getInt("trial_count")
    );

    public PlayResultDao(final DataSource dataSource, final NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id", "played_time");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public PlayResult insertPlayResult(final PlayResult playResult) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(playResult);

        int newId = (int) insertActor.executeAndReturnKeyHolder(parameterSource).getKeys().get("id");
        playResult.setId(newId);
        return playResult;
    }

    public List<PlayResult> selectAllResults() {
        String sql = "select id, trial_count, winners from play_result order by id desc";
        return namedParameterJdbcTemplate.query(sql, actorRowMapper);
    }
}
