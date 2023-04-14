package racingcar.repository;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

    private final SimpleJdbcInsert insertGame;

    public GameRepository(final DataSource dataSource) {
        this.insertGame = new SimpleJdbcInsert(dataSource)
                .withTableName("game")
                .usingColumns("trial_count")
                .usingGeneratedKeyColumns("id");
    }

    public long save(final int trialCount) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("trial_count", trialCount);
        return insertGame.executeAndReturnKey(parameters).longValue();
    }
}
