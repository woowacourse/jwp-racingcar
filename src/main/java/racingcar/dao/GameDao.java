package racingcar.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GameDao {

    private final SimpleJdbcInsert insertActor;

    public GameDao(final DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("id");
    }

    public int insertGame(final int tryTimes) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("trial_count", tryTimes);
        parameters.put("created_at", Timestamp.valueOf(LocalDateTime.now()));

        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}
