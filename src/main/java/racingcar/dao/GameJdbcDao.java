package racingcar.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.entity.GameEntity;
import racingcar.entity.GameId;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameJdbcDao implements GameDao {
    private final SimpleJdbcInsert insertActor;

    public GameJdbcDao(final JdbcTemplate jdbcTemplate) {
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public GameId saveAndGetGameId(final GameEntity game) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("trial", game.getTrial());
        parameters.put("created_at", game.getCreatedAt());

        return new GameId(insertActor.executeAndReturnKey(parameters).intValue());
    }
}
