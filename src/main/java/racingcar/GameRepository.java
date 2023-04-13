package racingcar;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import racingcar.service.TryCount;

@Repository
public class GameRepository {

    private final SimpleJdbcInsert insertGame;

    public GameRepository(final DataSource dataSource) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
    }

    public Number insertGame(final TryCount tryCount) {
        HashMap<String, Object> parameters = new HashMap<>();
        int trialCount = tryCount.getCount();
        parameters.put("trial_count", trialCount);
        return insertGame.executeAndReturnKey(parameters);
    }
}
