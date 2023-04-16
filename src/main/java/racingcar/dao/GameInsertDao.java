package racingcar.dao;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GameInsertDao {

    private final SimpleJdbcInsert insertGameActor;

    public GameInsertDao(DataSource dataSource) {
        this.insertGameActor = new SimpleJdbcInsert(dataSource)
                .withTableName("GAME")
                .usingGeneratedKeyColumns("id")
                .usingColumns("trial_count");
    }

    public int insertGame(Integer count) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", count);
        return insertGameActor.executeAndReturnKey(parameters).intValue();
    }
}
