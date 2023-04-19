package racingcar;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.service.TryCount;

@Repository
public class GameInsertDao {
    private final SimpleJdbcInsert insertGame;

    public GameInsertDao(DataSource dataSource) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
    }

    public Number insertGame(TryCount tryCount) {
        HashMap<String, Object> parameters = new HashMap<>();
        int trialCount = tryCount.getCount();
        parameters.put("trial_count", trialCount);
        return insertGame.executeAndReturnKey(parameters);
    }
}
