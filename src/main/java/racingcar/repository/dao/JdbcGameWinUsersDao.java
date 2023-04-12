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
import racingcar.repository.entity.GameWinUsersEntity;

@Repository
public class JdbcGameWinUsersDao implements GameWinUsersDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<GameWinUsersEntity> actorRowMapper = (resultSet, rowNum) -> new GameWinUsersEntity(
            resultSet.getLong("id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("user_id")
    );

    @Autowired
    public JdbcGameWinUsersDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final GameWinUsersEntity gameWinUsersEntity) {
        final String sql = "INSERT INTO game_win_users (game_id, users_id) VALUES (?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, gameWinUsersEntity.getGameId());
            preparedStatement.setLong(2, gameWinUsersEntity.getUsersId());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<GameWinUsersEntity> findByGameId(final long gameId) {
        final String sql = "SELECT id, game_id, users_id "
                + "FROM game_win_users "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
