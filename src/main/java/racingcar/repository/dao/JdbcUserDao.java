package racingcar.repository.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import racingcar.repository.entity.UserEntity;

@Repository
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final RowMapper<UserEntity> actorRowMapper = (resultSet, rowNum) -> new UserEntity(
        resultSet.getLong("id"),
        resultSet.getString("name")
    );

    public JdbcUserDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("users")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(final UserEntity userEntity) {
        final SqlParameterSource params = new BeanPropertySqlParameterSource(userEntity);
        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public UserEntity findById(final long id) {
        final String sql = "SELECT id, name FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, id);
    }

    @Override
    public UserEntity findByName(final String name) {
        final String sql = "SELECT id, name FROM users WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, name);
    }
}
