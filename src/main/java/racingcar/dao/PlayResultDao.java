package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

import javax.sql.DataSource;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayResultDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<PlayResult> entityRowMapper = (resultSet, rowNum) ->
            PlayResult.of(
                    resultSet.getLong("id"),
                    resultSet.getInt("trial_count"),
                    resultSet.getString("winners"),
                    resultSet.getTimestamp("created_at")
            );

    public long save(PlayResult playResult) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(playResult);
        return simpleJdbcInsert
                .executeAndReturnKey(sqlParameterSource)
                .longValue();
    }

    public PlayResult findById(long id) {
        String sql = "SELECT * FROM play_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }
}
