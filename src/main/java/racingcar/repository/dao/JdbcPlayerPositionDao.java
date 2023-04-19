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
import racingcar.repository.entity.PlayerPositionEntity;

@Repository
public class JdbcPlayerPositionDao implements PlayerPositionDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PlayerPositionEntity> actorRowMapper = (resultSet, rowNum) -> new PlayerPositionEntity(
            resultSet.getLong("player_position_id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("user_id"),
            resultSet.getInt("position")
    );

    @Autowired
    public JdbcPlayerPositionDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final PlayerPositionEntity playerPositionEntity) {
        final String sql = "INSERT INTO player_position (game_id, user_id, position) VALUES (?, ?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, playerPositionEntity.getGameId());
            preparedStatement.setLong(2, playerPositionEntity.getUserId());
            preparedStatement.setInt(3, playerPositionEntity.getPosition());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<PlayerPositionEntity> findByGameId(final long gameId) {
        final String sql = "SELECT player_position_id, game_id, user_id, position "
                + "FROM player_position "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
