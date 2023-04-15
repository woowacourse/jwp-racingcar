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
import racingcar.repository.entity.UsersEntity;

@Repository
public class JdbcUsersDao implements UsersDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<UsersEntity> actorRowMapper = (resultSet, rowNum) -> new UsersEntity(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    @Autowired
    public JdbcUsersDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final UsersEntity usersEntity) {
        final String sql = "INSERT INTO users (name) VALUES (?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, usersEntity.getName());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public UsersEntity findById(final long id) {
        final String sql = "SELECT id, name FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, id);
    }

    @Override
    public UsersEntity findByName(final String name) {
        final String sql = "SELECT id, name FROM users WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, name);
    }
}
