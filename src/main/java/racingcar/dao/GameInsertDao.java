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
                .usingGeneratedKeyColumns("game_id")
                .usingColumns("winners", "trial_count");}

    public int insertGame(String winners, Integer count) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("winners", winners);
        parameters.put("trial_count", count);
        return insertGameActor.executeAndReturnKey(parameters).intValue();
    }
}
