package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GameJdbcDao implements GameDao {
    private final SimpleJdbcInsert insertActor;
    public final JdbcTemplate jdbcTemplate;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Game> actorRowMapper = (resultSet, rowNum) -> new Game(
            resultSet.getInt("game_id"),
            resultSet.getInt("trial_count"),
            resultSet.getTimestamp("created_at").toLocalDateTime()
    );

    public int saveAndGetId(final Game game) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("trial", game.getTrial());
        parameters.put("created_at", game.getCreatedAt());

        return (int) insertActor.executeAndReturnKey(parameters).longValue();
    }

    @Override
    public List<Game> findAll() {
        final String sql = "SELECT game_id, created_at, trial_count FROM GAME";

        return jdbcTemplate.query(sql, actorRowMapper);
    }
}
