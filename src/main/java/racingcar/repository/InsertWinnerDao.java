package racingcar.repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class InsertWinnerDao {

    private final SimpleJdbcInsert insertActor;

    public InsertWinnerDao(final DataSource dataSource) {
        insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("winner")
                .usingGeneratedKeyColumns("winner_id");
    }

    public int save(final int gameId, final int winnerCarId) {
        final Map<String, Object> parameters = new HashMap<>(2);

        parameters.put("game_id", gameId);
        parameters.put("car_id", winnerCarId);

        return insertActor.executeAndReturnKey(parameters).intValue();
    }
}
