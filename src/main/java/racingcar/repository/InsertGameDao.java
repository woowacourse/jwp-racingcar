package racingcar.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class InsertGameDao {

    private final SimpleJdbcInsert insertActor;

    public InsertGameDao(final DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_id");
    }

    public int save(final int count) {
        final Map<String, Object> parameters = new HashMap<>(2);

        parameters.put("trial_count", count);
        parameters.put("created_at", LocalDateTime.now());

        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}
