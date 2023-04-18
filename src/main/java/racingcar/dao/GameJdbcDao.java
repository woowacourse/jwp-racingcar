package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class GameJdbcDao implements GameDao {
    private final SimpleJdbcInsert insertActor;
    public final JdbcTemplate jdbcTemplate;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    public Optional<Integer> saveAndGetId(final Game game) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("trial", game.getTrial());
        parameters.put("created_at", game.getCreatedAt());

        return Optional.of(insertActor.executeAndReturnKey(parameters).intValue());
    }
}
