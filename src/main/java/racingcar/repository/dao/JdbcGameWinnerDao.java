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
import racingcar.repository.entity.GameWinnerEntity;

@Repository
public class JdbcGameWinnerDao implements GameWinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<GameWinnerEntity> actorRowMapper = (resultSet, rowNum) -> new GameWinnerEntity(
            resultSet.getLong("game_winner_id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("user_id")
    );

    @Autowired
    public JdbcGameWinnerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final GameWinnerEntity gameWinnerEntity) {
        final String sql = "INSERT INTO game_winner (game_id, user_id) VALUES (?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, gameWinnerEntity.getGameId());
            preparedStatement.setLong(2, gameWinnerEntity.getUserId());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<GameWinnerEntity> findByGameId(final long gameId) {
        final String sql = "SELECT game_winner_id, game_id, user_id "
                + "FROM game_winner "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
