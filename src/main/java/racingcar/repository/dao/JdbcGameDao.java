package racingcar.repository.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.GameEntity;

@Repository
public class JdbcGameDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<GameEntity> actorRowMapper = (resultSet, rowNum) -> new GameEntity(
        resultSet.getLong("id"),
        resultSet.getInt("trial_count"),
        resultSet.getTimestamp("created_at")
    );

    public JdbcGameDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("game")
            .usingColumns("trial_count")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final GameEntity gameEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(gameEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public List<GameEntity> findAll() {
        final String sql = "SELECT id, trial_count, created_at FROM game";
        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
