package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.domain.GameResult;

import java.util.List;

@Repository
public class JdbcGameResultDao implements GameResultDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcGameResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game_result")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<GameResult> entityRowMapper = (resultSet, rowNum) ->
            new GameResult(
                    resultSet.getLong("id"),
                    resultSet.getInt("trial_count"),
                    resultSet.getTimestamp("created_at")
            );

    public long save(GameResult gameResult) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(gameResult);
        return simpleJdbcInsert
                .executeAndReturnKey(sqlParameterSource)
                .longValue();
    }

    public GameResult findById(long id) {
        String sql = "SELECT * FROM game_result WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, entityRowMapper, id);
    }

    public List<GameResult> findAll() {
        String sql = "SELECT * FROM game_result";
        return jdbcTemplate.query(sql, entityRowMapper);
    }
}
