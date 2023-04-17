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
import racingcar.repository.entity.WinnerEntity;

@Repository
public class JdbcWinnerDao implements WinnerDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<WinnerEntity> actorRowMapper = (resultSet, rowNum) -> new WinnerEntity(
            resultSet.getLong("id"),
            resultSet.getLong("game_id"),
            resultSet.getLong("users_id")
    );

    @Autowired
    public JdbcWinnerDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long save(final WinnerEntity winnerEntity) {
        final String sql = "INSERT INTO winner (game_id, users_id) VALUES (?, ?)";
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, winnerEntity.getGameId());
            preparedStatement.setLong(2, winnerEntity.getUserId());
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<WinnerEntity> findByGameId(final long gameId) {
        final String sql = "SELECT id, game_id, users_id "
                + "FROM winner "
                + "WHERE game_id = ?";
        return jdbcTemplate.query(sql, actorRowMapper, gameId);
    }
}
