package racingcar.repository.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.PlayerEntity;

@Repository
public class JdbcPlayerDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<PlayerEntity> actorRowMapper = (resultSet, rowNum) -> new PlayerEntity(
        resultSet.getLong("id"),
        resultSet.getString("name")
    );

    public JdbcPlayerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("player")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final PlayerEntity playerEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(playerEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public boolean existsByName(final String name) {
        final String sql = "SELECT EXISTS (SELECT * FROM player WHERE name = ?) AS P";
        return jdbcTemplate.queryForObject(sql, Boolean.class, name);
    }

    @Override
    public PlayerEntity findByName(final String name) {
        final String sql = "SELECT id, name FROM player WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, name);
    }
}
