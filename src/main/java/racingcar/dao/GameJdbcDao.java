package racingcar.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import racingcar.entity.Game;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class GameJdbcDao implements GameDao {
    private final SimpleJdbcInsert insertActor;

    public GameJdbcDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    public int save(final Game game) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("trial", game.getTrial());
        parameters.put("created_at", game.getCreatedAt());

        return (int) insertActor.executeAndReturnKey(parameters).longValue();
    }
}
