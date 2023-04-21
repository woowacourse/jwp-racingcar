package racingcar.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import racingcar.dao.entity.WinnerEntity;

public class InsertWinnerDao {

    private final SimpleJdbcInsert insertActor;

    public InsertWinnerDao(final JdbcTemplate jdbcTemplate) {
        insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("winner")
                .usingGeneratedKeyColumns("winner_id");
    }

    public void insertAll(final List<WinnerEntity> winnerEntities) {
        @SuppressWarnings("unchecked") final HashMap<String, Object>[] batch = winnerEntities.stream()
                .map(this::toMap)
                .toArray(HashMap[]::new);
        insertActor.executeBatch(batch);
    }

    private HashMap<String, Object> toMap(final WinnerEntity winnerEntity) {
        final HashMap<String, Object> parameters = new HashMap<>(3);
        parameters.put("car_id", winnerEntity.getCarId().getValue());
        parameters.put("game_id", winnerEntity.getGameId().getValue());
        return parameters;
    }
}
