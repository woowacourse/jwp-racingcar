package racingcar.dao;

import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PlayRecordsDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertActor;

    public PlayRecordsDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("play_records")
                .usingGeneratedKeyColumns("id")
                .usingColumns("trial_count");
    }

    public long insertAndReturnId(final int count) {
        final Number savedId = insertActor.executeAndReturnKey(Map.of("trial_count", count));
        return savedId.longValue();
    }

    public void clear() {
        jdbcTemplate.update("DELETE FROM play_records");
    }
}
