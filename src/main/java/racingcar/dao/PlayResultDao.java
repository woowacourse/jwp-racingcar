package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.PlayResult;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public PlayResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("play_result")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<PlayResult> entityRowMapper = (resultSet, rowNum) ->
            PlayResult.of(
                    resultSet.getInt("id"),
                    resultSet.getInt("trial_count"),
                    resultSet.getString("winners"),
                    resultSet.getTimestamp("created_at")
            );

    public int save(PlayResult playResult) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(playResult);
        return simpleJdbcInsert
                .executeAndReturnKey(sqlParameterSource)
                .intValue();
    }

    public PlayResult findById(int id) {
        String sql = "SELECT * FROM play_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }

    public List<PlayResult> findAll() {
        String sql = "SELECT * FROM play_result";
        return jdbcTemplate.query(sql, entityRowMapper);
    }

    public void deleteAll() {
        String sql = "DELETE FROM play_result";
        jdbcTemplate.update(sql);
    }
}
