package racingcar.repository.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.PositionEntity;

@Repository
public class JdbcPositionDao implements PositionDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<PositionEntity> actorRowMapper = (resultSet, rowNum) -> new PositionEntity(
        resultSet.getLong("id"),
        resultSet.getLong("game_id"),
        resultSet.getLong("users_id"),
        resultSet.getInt("position")
    );

    public JdbcPositionDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("position")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final PositionEntity positionEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(positionEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public List<PositionEntity> findByGameId(final long gameId) {
        final String sql = "SELECT id, game_id, users_id, position "
                + "FROM position "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
