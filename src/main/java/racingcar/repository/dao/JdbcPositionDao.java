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
import racingcar.repository.entity.PositionEntity;

@Repository
public class JdbcPositionDao implements PositionDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PositionEntity> actorRowMapper = (resultSet, rowNum) -> new PositionEntity(
            resultSet.getLong("id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("user_id"),
            resultSet.getInt("position")
    );

    @Autowired
    public JdbcPositionDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final PositionEntity positionEntity) {
        final String sql = "INSERT INTO position (game_id, user_id, position) VALUES (?, ?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, positionEntity.getGameId());
            preparedStatement.setLong(2, positionEntity.getUserId());
            preparedStatement.setInt(3, positionEntity.getPosition());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<PositionEntity> findByGameId(final long gameId) {
        final String sql = "SELECT id, game_id, user_id, position "
                + "FROM position "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
