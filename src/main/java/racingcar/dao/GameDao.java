package racingcar.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GameDao {
    private SimpleJdbcInsert simpleJdbcInsert;

    public GameDao(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingGeneratedKeyColumns("game_number", "created_at");
    }

    public int saveGame(int trialCount) {
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        return (int) simpleJdbcInsert.executeAndReturnKeyHolder(parameters).getKeys().get("game_number");
    }
}
