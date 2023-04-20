package racingcar.repository.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.repository.entity.PlayerEntity;

@Repository
public class JdbcPlayerDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PlayerEntity> actorRowMapper = (resultSet, rowNum) -> new PlayerEntity(
            resultSet.getLong("user_id"),
            resultSet.getString("name")
    );

    public JdbcPlayerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final PlayerEntity playerEntity) {
        final String sql = "INSERT INTO player (name) VALUES (?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, playerEntity.getName());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public PlayerEntity findById(final long id) {
        final String sql = "SELECT user_id, name FROM player WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, id);
    }

    @Override
    public PlayerEntity findByName(final String name) {
        final String sql = "SELECT user_id, name FROM player WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, actorRowMapper, name);
    }
}
