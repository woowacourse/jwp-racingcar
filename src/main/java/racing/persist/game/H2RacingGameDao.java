package racing.persist.game;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class H2RacingGameDao implements RacingGameDao {

    private final JdbcTemplate jdbcTemplate;

    public H2RacingGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long saveGame(int count) {
        String saveGameQuery = "INSERT INTO games(count, create_time) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(saveGameQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, count);
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            return preparedStatement;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public RacingGameEntity findGameById(Long gameId) {
        String selectGameByIdQuery = "SELECT * FROM games AS g WHERE g.game_id = ?";

        return jdbcTemplate.queryForObject(selectGameByIdQuery, (rs, rowNum) -> {
            int carName = rs.getInt("count");
            LocalDateTime createTime = rs.getObject("create_time", LocalDateTime.class);

            return new RacingGameEntity(gameId, createTime, carName);
        }, gameId);
    }

    @Override
    public List<RacingGameEntity> findAllGameByRecent() {
        String selectGamesInIdQuery = "SELECT * FROM games WHERE game_id ORDER BY create_time DESC";
        return jdbcTemplate.query(selectGamesInIdQuery, (rs, rowNum) -> {
            Long gameId = rs.getLong("game_id");
            int carName = rs.getInt("count");
            LocalDateTime createTime = rs.getObject("create_time", LocalDateTime.class);

            return new RacingGameEntity(gameId, createTime, carName);
        });
    }
}
