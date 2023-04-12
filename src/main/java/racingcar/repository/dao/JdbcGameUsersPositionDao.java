package racingcar.repository.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.repository.entity.GameUsersPositionEntity;

@Repository
public class JdbcGameUsersPositionDao implements GameUsersPositionDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<GameUsersPositionEntity> actorRowMapper = (resultSet, rowNum) -> new GameUsersPositionEntity(
            resultSet.getLong("id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("user_id"),
            resultSet.getInt("position")
    );

    @Autowired
    public JdbcGameUsersPositionDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final GameUsersPositionEntity gameUsersPositionEntity) {
        final String sql = "INSERT INTO game_users_position (game_id, users_id, position) VALUES (?, ?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, gameUsersPositionEntity.getGameId());
            preparedStatement.setLong(2, gameUsersPositionEntity.getUsersId());
            preparedStatement.setInt(3, gameUsersPositionEntity.getPosition());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<GameUsersPositionEntity> findByGameId(final long gameId) {
        final String sql = "SELECT id, game_id, users_id, position "
                + "FROM game_users_position "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
