package racingcar.repository.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.repository.entity.UserEntity;

@Repository
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UserEntity> actorRowMapper = (resultSet, rowNum) -> new UserEntity(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    @Autowired
    public JdbcUserDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final UserEntity userEntity) {
        final String sql = "INSERT INTO users (name) VALUES (?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userEntity.getName());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
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
