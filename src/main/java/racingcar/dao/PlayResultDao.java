package racingcar.dao;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PlayResultDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public PlayResultDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("PLAY_RESULT")
                .usingGeneratedKeyColumns("id")
                .usingColumns("trial_count", "winners");
    }

    public long insert(int count) {
        Number savedId = insertActor.executeAndReturnKey(Map.of("trial_count", count));
        return savedId.longValue();
    }

    public void update(long id, String winners) {
        jdbcTemplate.update("UPDATE play_result SET winners = ? WHERE id = " + id, winners);

    }
}
