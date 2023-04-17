package racingcar.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dao.entity.GameEntity;

public class InsertGameDao {

    private final SimpleJdbcInsert insertActor;

    public InsertGameDao(final DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public GameEntity insert(final GameEntity gameEntity) {
        final Map<String, Object> parameters = new HashMap<>(2);
        final LocalDateTime createAt = LocalDateTime.now();

        parameters.put("trial_count", gameEntity.getCount());
        parameters.put("created_at", createAt);

        return new GameEntity(insertActor.executeAndReturnKey(parameters).intValue(), gameEntity);
    }
}
