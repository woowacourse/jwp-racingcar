package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dao.entity.GameEntity;

public class InsertGameDao {

    private final SimpleJdbcInsert insertActor;

    public InsertGameDao(final JdbcTemplate jdbcTemplate) {
        insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public GameEntity insert(final GameEntity gameEntity) {
        final Map<String, Object> parameters = new HashMap<>(3);
        final LocalDateTime createAt = LocalDateTime.now();
        parameters.put("game_id", gameEntity.getGameId().getValue());
        parameters.put("trial_count", gameEntity.getTrialCount());
        parameters.put("created_at", createAt);

        return new GameEntity(insertActor.executeAndReturnKey(parameters).intValue(), gameEntity.getTrialCount());
    }
}
